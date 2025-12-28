package com.autoparts.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autoparts.marketplace.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
