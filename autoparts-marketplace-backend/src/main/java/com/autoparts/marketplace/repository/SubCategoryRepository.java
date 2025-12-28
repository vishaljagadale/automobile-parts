package com.autoparts.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoparts.marketplace.entity.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
}
