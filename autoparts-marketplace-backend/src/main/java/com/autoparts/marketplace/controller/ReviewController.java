package com.autoparts.marketplace.controller;

import com.autoparts.marketplace.dto.ReviewDTO;
import com.autoparts.marketplace.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDTO> addReview(@RequestBody @Valid ReviewDTO reviewDTO) {
        ReviewDTO review = reviewService.addReview(
            reviewDTO.getUserId(),
            reviewDTO.getProductId(),
            reviewDTO.getRating(),
            reviewDTO.getComment()
        );
        return ResponseEntity.ok(review);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProduct(productId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(userId));
    }

    @PutMapping("/{reviewId}/approve")
    public ResponseEntity<ReviewDTO> approveReview(@PathVariable Long reviewId, @RequestParam boolean approved) {
        ReviewDTO review = reviewService.approveReview(reviewId, approved);
        if (review == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(review);
    }
}