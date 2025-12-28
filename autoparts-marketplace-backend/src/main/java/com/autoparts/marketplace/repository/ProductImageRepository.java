package com.autoparts.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoparts.marketplace.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
