import { Injectable } from '@angular/core';
import { ApiService } from '../../core/api.service';
import { Observable } from 'rxjs';
import { User } from '../../core/models';

@Injectable({ providedIn: 'root' })
export class UsersService {
  constructor(private api: ApiService) {}

  getUsers(): Observable<any[]> {
    return this.api.get<any[]>('/users');
  }

  createUser(user: User): Observable<User> {
    return this.api.post<User>('/users', user);
  }

  getUserById(id: number): Observable<any> {
    return this.api.get<any>(`/users/${id}`);
  }

  updateUser(id: number, user: User): Observable<User> {
    return this.api.put<User>(`/users/${id}`, user);
  }

  deleteUser(id: number): Observable<any> {
    return this.api.delete<any>(`/users/${id}`);
  }

  approveSeller(sellerId: number, approved: boolean): Observable<any> {
    return this.api.put<any>(`/api/v1/users/${sellerId}/approve?approved=${approved}`, {});
  }

  blockUser(userId: number, blocked: boolean): Observable<any> {
    return this.api.put<any>(`/api/v1/users/${userId}/block?blocked=${blocked}`, {});
  }
}