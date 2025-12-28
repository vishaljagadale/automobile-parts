package com.autoparts.marketplace.service;

import com.autoparts.marketplace.dto.ReviewDTO;
import com.autoparts.marketplace.entity.*;
import com.autoparts.marketplace.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public ReviewDTO addReview(Long userId, Long productId, int rating, String comment) {
        Review review = new Review();
        review.setUser(userRepository.findById(userId).orElse(null));
        review.setProduct(productRepository.findById(productId).orElse(null));
        review.setRating(rating);
        review.setComment(comment);
        review.setCreatedAt(LocalDateTime.now());
        review.setApproved(true); // Default to approved, can be set to false for moderation
        review = reviewRepository.save(review);
        return toDTO(review);
    }

    public List<ReviewDTO> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ReviewDTO> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ReviewDTO approveReview(Long reviewId, boolean approved) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) return null;
        review.setApproved(approved);
        review = reviewRepository.save(review);
        return toDTO(review);
    }

    private ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setUserId(review.getUser().getId());
        dto.setProductId(review.getProduct().getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setApproved(review.isApproved());
        return dto;
    }
}
