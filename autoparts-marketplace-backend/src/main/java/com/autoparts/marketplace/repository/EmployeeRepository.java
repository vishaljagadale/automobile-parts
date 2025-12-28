package com.autoparts.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoparts.marketplace.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
