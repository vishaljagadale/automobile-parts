import { Injectable } from '@angular/core';
import { ApiService } from '../../core/api.service';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ReviewService {
  constructor(private api: ApiService) {}

  addReview(review: any): Observable<any> {
    // review should include: userId, productId, sellerId, rating, comment, approved, timestamp
    return this.api.post<any>(`/api/v1/reviews`, review);
  }

  getReviewsByProduct(productId: number): Observable<any[]> {
    return this.api.get<any[]>(`/api/v1/reviews/product/${productId}`);
  }

  getReviewsByUser(userId: number): Observable<any[]> {
    return this.api.get<any[]>(`/api/v1/reviews/user/${userId}`);
  }

  approveReview(reviewId: number, approved: boolean): Observable<any> {
    return this.api.put<any>(`/api/v1/reviews/${reviewId}/approve?approved=${approved}`, {});
  }
}