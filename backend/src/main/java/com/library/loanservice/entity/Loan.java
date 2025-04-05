package com.library.loanservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Data
public class Loan {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @NotNull
    @Column(name = "book_id", nullable = false)
    private Long bookId;
    
    @NotNull
    @Column(name = "loan_date", nullable = false)
    private LocalDate loanDate;
    
    @NotNull
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private LoanStatus status;
}