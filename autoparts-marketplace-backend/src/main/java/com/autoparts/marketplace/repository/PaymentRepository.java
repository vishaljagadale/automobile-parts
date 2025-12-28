package com.autoparts.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoparts.marketplace.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
