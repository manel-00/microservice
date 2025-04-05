package com.library.loanservice.controller;

import com.library.loanservice.entity.Loan;
import com.library.loanservice.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LoanController {
    
    private final LoanService loanService;
    
    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }
    
    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }
    
    @GetMapping("/user/{userId}")
    public List<Loan> getLoansByUserId(@PathVariable Long userId) {
        return loanService.getLoansByUserId(userId);
    }
    
    @GetMapping("/book/{bookId}")
    public List<Loan> getLoansByBookId(@PathVariable Long bookId) {
        return loanService.getLoansByBookId(bookId);
    }
    
    @PostMapping
    public Loan createLoan(@Valid @RequestBody Loan loan) {
        return loanService.createLoan(loan);
    }
    
    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable Long id, @Valid @RequestBody Loan loan) {
        return loanService.updateLoan(id, loan);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }
}