import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LoanService } from '../../services/loan.service';
import { Loan, LoanStatus } from '../../models/loan.model';

@Component({
  selector: 'app-loan-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <form (ngSubmit)="onSubmit()" class="bg-white p-6 rounded-lg shadow-md mb-6">
      <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div>
          <label class="block text-sm font-medium text-gray-700">User ID</label>
          <input type="number" [(ngModel)]="loan.userId" name="userId" 
                 class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">Book ID</label>
          <input type="number" [(ngModel)]="loan.bookId" name="bookId"
                 class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">Loan Date</label>
          <input type="date" [(ngModel)]="loan.loanDate" name="loanDate"
                 class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">Due Date</label>
          <input type="date" [(ngModel)]="loan.dueDate" name="dueDate"
                 class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
        </div>
        <div>
          <label class="block text-sm font-medium text-gray-700">Status</label>
          <select [(ngModel)]="loan.status" name="status"
                  class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500">
            <option [ngValue]="LoanStatus.ACTIVE">Active</option>
            <option [ngValue]="LoanStatus.RETURNED">Returned</option>
            <option [ngValue]="LoanStatus.OVERDUE">Overdue</option>
          </select>
        </div>
      </div>
      <div class="mt-4">
        <button type="submit" 
                class="bg-indigo-600 text-white px-4 py-2 rounded hover:bg-indigo-700 transition">
          Create Loan
        </button>
      </div>
    </form>
  `
})
export class LoanFormComponent {
  @Output() loanCreated = new EventEmitter<Loan>();
  
  LoanStatus = LoanStatus;
  
  loan: Loan = {
    userId: 0,
    bookId: 0,
    loanDate: new Date().toISOString().split('T')[0],
    dueDate: new Date().toISOString().split('T')[0],
    status: LoanStatus.ACTIVE
  };

  constructor(private loanService: LoanService) {}

  onSubmit() {
    this.loanService.createLoan(this.loan).subscribe({
      next: (createdLoan) => {
        this.loanCreated.emit(createdLoan);
        this.resetForm();
      },
      error: (error) => console.error('Error creating loan:', error)
    });
  }

  private resetForm() {
    this.loan = {
      userId: 0,
      bookId: 0,
      loanDate: new Date().toISOString().split('T')[0],
      dueDate: new Date().toISOString().split('T')[0],
      status: LoanStatus.ACTIVE
    };
  }
}