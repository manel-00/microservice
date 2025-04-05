import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { LoanListComponent } from './app/components/loan-list/loan-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [LoanListComponent],
  template: `
    <div class="min-h-screen bg-gray-50">
      <app-loan-list></app-loan-list>
    </div>
  `
})
export class App {
  name = 'Library Loan Management';
}

bootstrapApplication(App, {
  providers: [
    provideHttpClient()
  ]
}).catch(err => console.error(err));