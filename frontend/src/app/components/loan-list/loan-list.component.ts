import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoanService } from '../../services/loan.service';
import { Loan, LoanStatus } from '../../models/loan.model';
import { LoanFormComponent } from '../loan-form/loan-form.component';

@Component({
  selector: 'app-loan-list',
  standalone: true,
  imports: [CommonModule, LoanFormComponent],
  template: `
    <div class="container mx-auto px-4 py-8">
      <h1 class="text-3xl font-bold mb-6">Library Loans</h1>
      
      <app-loan-form (loanCreated)="onLoanCreated($event)"></app-loan-form>

      <div class="mt-8">
        <h2 class="text-2xl font-semibold mb-4">All Loans</h2>
        <div class="grid gap-4">
          <div *ngFor="let loan of loans" class="bg-white p-6 rounded-lg shadow-md">
            <div class="flex justify-between items-start">
              <div>
                <p class="text-gray-600">User ID: {{loan.userId}}</p>
                <p class="text-gray-600">Book ID: {{loan.bookId}}</p>
                <p class="text-gray-600">Loan Date: {{loan.loanDate}}</p>
                <p class="text-gray-600">Due Date: {{loan.dueDate}}</p>
              </div>
              <span [ngClass]="{
                'bg-green-100 text-green-800': loan.status === 'ACTIVE',
                'bg-blue-100 text-blue-800': loan.status === 'RETURNED',
                'bg-red-100 text-red-800': loan.status === 'OVERDUE'
              }" class="px-3 py-1 rounded-full text-sm font-semibold">
                {{loan.status}}
              </span>
            </div>
            <div class="mt-4 flex gap-2">
              <button (click)="deleteLoan(loan.id!)" 
                      class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600 transition">
                Delete
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  `
})
export class LoanListComponent implements OnInit {
  loans: Loan[] = [];

  constructor(private loanService: LoanService) {}

  ngOnInit() {
    this.loadLoans();
  }

  loadLoans() {
    this.loanService.getLoans().subscribe({
      next: (loans) => this.loans = loans,
      error: (error) => console.error('Error loading loans:', error)
    });
  }

  deleteLoan(id: number) {
    this.loanService.deleteLoan(id).subscribe({
      next: () => this.loadLoans(),
      error: (error) => console.error('Error deleting loan:', error)
    });
  }

  onLoanCreated(loan: Loan) {
    this.loadLoans();
  }
}