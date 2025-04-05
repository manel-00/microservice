package com.library.loanservice.service;

import com.library.loanservice.entity.Loan;
import com.library.loanservice.entity.LoanStatus;
import com.library.loanservice.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

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
    void getAllLoans_ShouldReturnAllLoans() {
        when(loanRepository.findAll()).thenReturn(List.of(testLoan));
        
        List<Loan> result = loanService.getAllLoans();
        
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(loanRepository).findAll();
    }

    @Test
    void getLoanById_WithValidId_ShouldReturnLoan() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(testLoan));
        
        Loan result = loanService.getLoanById(1L);
        
        assertNotNull(result);
        assertEquals(testLoan.getId(), result.getId());
        verify(loanRepository).findById(1L);
    }

    @Test
    void getLoanById_WithInvalidId_ShouldThrowException() {
        when(loanRepository.findById(99L)).thenReturn(Optional.empty());
        
        assertThrows(EntityNotFoundException.class, () -> loanService.getLoanById(99L));
        verify(loanRepository).findById(99L);
    }

    @Test
    void createLoan_ShouldReturnSavedLoan() {
        when(loanRepository.save(any(Loan.class))).thenReturn(testLoan);
        
        Loan result = loanService.createLoan(testLoan);
        
        assertNotNull(result);
        assertEquals(testLoan.getId(), result.getId());
        verify(loanRepository).save(testLoan);
    }

    @Test
    void updateLoan_WithValidId_ShouldReturnUpdatedLoan() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(testLoan));
        when(loanRepository.save(any(Loan.class))).thenReturn(testLoan);
        
        Loan result = loanService.updateLoan(1L, testLoan);
        
        assertNotNull(result);
        assertEquals(testLoan.getId(), result.getId());
        verify(loanRepository).findById(1L);
        verify(loanRepository).save(testLoan);
    }

    @Test
    void deleteLoan_WithValidId_ShouldDeleteLoan() {
        when(loanRepository.existsById(1L)).thenReturn(true);
        
        loanService.deleteLoan(1L);
        
        verify(loanRepository).deleteById(1L);
    }

    @Test
    void deleteLoan_WithInvalidId_ShouldThrowException() {
        when(loanRepository.existsById(99L)).thenReturn(false);
        
        assertThrows(EntityNotFoundException.class, () -> loanService.deleteLoan(99L));
        verify(loanRepository, never()).deleteById(any());
    }
}