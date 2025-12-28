package com.autoparts.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoparts.marketplace.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
