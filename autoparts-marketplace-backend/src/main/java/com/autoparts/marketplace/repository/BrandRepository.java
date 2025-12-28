package com.autoparts.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoparts.marketplace.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
