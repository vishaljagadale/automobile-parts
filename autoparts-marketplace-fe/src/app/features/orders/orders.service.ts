import { Injectable } from '@angular/core';
import { ApiService } from '../../core/api.service';
import { Observable } from 'rxjs';
import { Order } from '../../core/models';

@Injectable({ providedIn: 'root' })
export class OrdersService {
  constructor(private api: ApiService) {}

  getOrders(): Observable<any[]> {
    return this.api.get<any[]>('/orders');
  }

  placeOrder(userId: number): Observable<any> {
    return this.api.post<any>(`/api/v1/orders/place?userId=${userId}`, {});
  }

  getOrderById(orderId: number): Observable<any> {
    return this.api.get<any>(`/orders/${orderId}`);
  }

  getOrdersByUserId(userId: number): Observable<any[]> {
    return this.api.get<any[]>(`/orders/user/${userId}`);
  }
}