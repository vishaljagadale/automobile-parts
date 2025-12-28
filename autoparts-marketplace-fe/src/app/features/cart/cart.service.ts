import { Injectable } from '@angular/core';
import { ApiService } from '../../core/api.service';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CartService {
  constructor(private api: ApiService) {}

  getCart(userId: number): Observable<any> {
    return this.api.get<any>(`/api/v1/cart/${userId}`);
  }

  addToCart(userId: number, productId: number, quantity: number): Observable<any> {
    return this.api.post<any>(`/api/v1/cart/add?userId=${userId}&productId=${productId}&quantity=${quantity}`, {});
  }

  removeItem(cartItemId: number): Observable<void> {
    return this.api.delete<void>(`/api/v1/cart/item/${cartItemId}`);
  }
}
