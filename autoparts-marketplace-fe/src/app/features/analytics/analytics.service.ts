import { Injectable } from '@angular/core';
import { ApiService } from '../../core/api.service';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AnalyticsService {
  constructor(private api: ApiService) {}

  getSalesAnalytics(): Observable<any> {
    return this.api.get<any>('/api/v1/analytics/sales');
  }

  getTopProducts(): Observable<any> {
    return this.api.get<any>('/api/v1/analytics/top-products');
  }

  getRevenueAnalytics(): Observable<any> {
    return this.api.get<any>('/api/v1/analytics/revenue');
  }

  getReturnsAnalytics(): Observable<any> {
    return this.api.get<any>('/api/v1/analytics/returns');
  }

  getAnalytics(params: any): Observable<any> {
    // params can include dateRange, metricsType, etc.
    const query = new URLSearchParams(params).toString();
    return this.api.get<any>(`/api/v1/analytics?${query}`);
  }
}