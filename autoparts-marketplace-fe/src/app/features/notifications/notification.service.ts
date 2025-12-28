import { Injectable } from '@angular/core';
import { ApiService } from '../../core/api.service';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class NotificationService {
  constructor(private api: ApiService) {}

  sendEmail(to: string, subject: string, body: string): Observable<any> {
    return this.api.post<any>(`/api/v1/notifications/email?to=${encodeURIComponent(to)}&subject=${encodeURIComponent(subject)}&body=${encodeURIComponent(body)}`, {});
  }

  sendNotification(notification: any): Observable<any> {
    // notification should include: id, userId, type, message, read, timestamp
    return this.api.post<any>(`/api/v1/notifications`, notification);
  }
}