package com.autoparts.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoparts.marketplace.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
