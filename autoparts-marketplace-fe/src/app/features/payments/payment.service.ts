import { Injectable } from '@angular/core';
import { ApiService } from '../../core/api.service';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PaymentService {
  constructor(private api: ApiService) {}

  pay(payment: any): Observable<any> {
    // payment should include: orderId, userId, amount, paymentMethod, status, transactionId, timestamp
    return this.api.post<any>(`/api/v1/payments/pay`, payment);
  }

  getPayment(paymentId: number): Observable<any> {
    return this.api.get<any>(`/api/v1/payments/${paymentId}`);
  }
}