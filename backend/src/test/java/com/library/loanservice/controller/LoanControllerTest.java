package com.library.loanservice.controller;

import com.library.loanservice.entity.Loan;
import com.library.loanservice.entity.LoanStatus;
import com.library.loanservice.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanController.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @Autowired
    private ObjectMapper objectMapper;

    private Loan testLoan;

    @BeforeEach
    void setUp() {
        testLoan = new Loan();
        testLoan.setId(1L);
        testLoan.setUserId(1L);
        testLoan.setBookId(1L);
        testLoan.setLoanDate(LocalDate.now());
        testLoan.setDueDate(LocalDate.now().plusDays(14));
        testLoan.setStatus(LoanStatus.ACTIVE);
    }

    @Test
    void getAllLoans_ShouldReturnLoans() throws Exception {
        when(loanService.getAllLoans()).thenReturn(List.of(testLoan));

        mockMvc.perform(get("/api/loans"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(testLoan.getId()));
    }

    @Test
    void getLoanById_WithValidId_ShouldReturnLoan() throws Exception {
        when(loanService.getLoanById(1L)).thenReturn(testLoan);

        mockMvc.perform(get("/api/loans/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(testLoan.getId()));
    }

    @Test
    void createLoan_WithValidLoan_ShouldReturnCreatedLoan() throws Exception {
        when(loanService.createLoan(any(Loan.class))).thenReturn(testLoan);

        mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testLoan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testLoan.getId()));
    }

    @Test
    void updateLoan_WithValidLoan_ShouldReturnUpdatedLoan() throws Exception {
        when(loanService.updateLoan(eq(1L), any(Loan.class))).thenReturn(testLoan);

        mockMvc.perform(put("/api/loans/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testLoan)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testLoan.getId()));
    }

    @Test
    void deleteLoan_WithValidId_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/loans/1"))
                .andExpect(status().isNoContent());
    }
}