import { Component, OnInit } from '@angular/core';
import { AnalyticsService } from './analytics.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-analytics-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './analytics-list.component.html',
  styleUrls: ['./analytics-list.component.css']
})
export class AnalyticsListComponent implements OnInit {
  analytics: any[] = [];
  error: string = '';

  constructor(private analyticsService: AnalyticsService) {}

  ngOnInit(): void {
    this.analyticsService.getAnalytics().subscribe({
      next: analytics => this.analytics = analytics,
      error: err => this.error = 'Failed to load analytics'
    });
  }
}
