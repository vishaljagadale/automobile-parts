package com.autoparts.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoparts.marketplace.entity.SellerProfile;

public interface SellerProfileRepository extends JpaRepository<SellerProfile, Long> {
}
