package com.library.loanservice.service;

import com.library.loanservice.entity.Loan;
import com.library.loanservice.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    
    private final LoanRepository loanRepository;
    
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
    
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + id));
    }
    
    public List<Loan> getLoansByUserId(Long userId) {
        return loanRepository.findByUserId(userId);
    }
    
    public List<Loan> getLoansByBookId(Long bookId) {
        return loanRepository.findByBookId(bookId);
    }
    
    @Transactional
    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }
    
    @Transactional
    public Loan updateLoan(Long id, Loan loan) {
        Loan existingLoan = getLoanById(id);
        loan.setId(existingLoan.getId());
        return loanRepository.save(loan);
    }

     public double calculateFine(Loan loan) {
        // Example fine calculation logic
        LocalDate returnDate = loan.getReturnDate();
        LocalDate dueDate = loan.getDueDate();

        if (returnDate != null && returnDate.isAfter(dueDate)) {
            // Calculate fine, for example $1 per day late
            long daysLate = java.time.temporal.ChronoUnit.DAYS.between(dueDate, returnDate);
            return daysLate * 1.0;  // Fine amount calculation logic
        }
        return 0.0;  // No fine if returned on time
    }

    
    @Transactional
    public void deleteLoan(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new EntityNotFoundException("Loan not found with id: " + id);
        }
        loanRepository.deleteById(id);
    }
}
