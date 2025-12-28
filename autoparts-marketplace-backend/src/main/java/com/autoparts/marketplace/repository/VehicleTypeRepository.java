package com.autoparts.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoparts.marketplace.entity.VehicleType;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
}
