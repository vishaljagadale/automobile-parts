package com.autoparts.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoparts.marketplace.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
