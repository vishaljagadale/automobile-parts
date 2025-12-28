import { Injectable } from '@angular/core';
import { ApiService } from '../../core/api.service';
import { Observable } from 'rxjs';
import { Product } from '../../core/models';

@Injectable({ providedIn: 'root' })
export class ProductsService {
  constructor(private api: ApiService) {}

  getProducts(): Observable<any[]> {
    return this.api.get<any[]>('/products');
  }

  getProductById(id: number): Observable<any> {
    return this.api.get<any>(`/products/${id}`);
  }

  addProduct(product: any): Observable<any> {
    // If imageUrls is a string, convert to Set<string>
    if (typeof product.imageUrls === 'string') {
      product.imageUrls = new Set(product.imageUrls.split(',').map((url: string) => url.trim()));
    }
    return this.api.post<any>('/products', product);
  }

  deleteProduct(id: number): Observable<void> {
    return this.api.delete<void>(`/products/${id}`);
  }

  searchProducts(filters: any): Observable<any> {
    const params = new URLSearchParams(filters).toString();
    return this.api.get<any>(`/products/search?${params}`);
  }
}