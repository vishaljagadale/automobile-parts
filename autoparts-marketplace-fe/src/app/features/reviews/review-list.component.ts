import { Component, OnInit } from '@angular/core';
import { ReviewService } from './review.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-review-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './review-list.component.html',
  styleUrls: ['./review-list.component.css']
})
export class ReviewListComponent implements OnInit {
  reviews: any[] = [];
  error: string = '';

  constructor(private reviewService: ReviewService) {}

  ngOnInit(): void {
    this.reviewService.getReviews().subscribe({
      next: reviews => this.reviews = reviews,
      error: err => this.error = 'Failed to load reviews'
    });
  }
}
