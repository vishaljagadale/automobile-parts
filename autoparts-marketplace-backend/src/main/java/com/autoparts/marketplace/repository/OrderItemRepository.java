package com.autoparts.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoparts.marketplace.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
