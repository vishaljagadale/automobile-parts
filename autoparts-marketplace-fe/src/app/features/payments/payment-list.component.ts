import { Component, OnInit } from '@angular/core';
import { PaymentService } from './payment.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-payment-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.css']
})
export class PaymentListComponent implements OnInit {
  payments: any[] = [];
  error: string = '';

  constructor(private paymentService: PaymentService) {}

  ngOnInit(): void {
    this.paymentService.getPayments().subscribe({
      next: payments => this.payments = payments,
      error: err => this.error = 'Failed to load payments'
    });
  }
}
