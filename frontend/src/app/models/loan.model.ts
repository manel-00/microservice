export enum LoanStatus {
  ACTIVE = 'ACTIVE',
  RETURNED = 'RETURNED',
  OVERDUE = 'OVERDUE'
}

export interface Loan {
  id?: number;
  userId: number;
  bookId: number;
  loanDate: string;
  dueDate: string;
  status: LoanStatus;
}