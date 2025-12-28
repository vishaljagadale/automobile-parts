package com.autoparts.marketplace.service;

import com.autoparts.marketplace.dto.UserDTO;
import com.autoparts.marketplace.entity.Role;
import com.autoparts.marketplace.entity.User;
import com.autoparts.marketplace.exception.ValidationException;
import com.autoparts.marketplace.repository.RoleRepository;
import com.autoparts.marketplace.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(String username, String email, String password, Set<String> roleNames) {
        // Validate username
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("username", "Username is required");
        }
        if (username.length() < 3) {
            throw new ValidationException("username", "Username must be at least 3 characters");
        }
        if (userRepository.findByUsername(username).isPresent()) {
            throw new ValidationException("username", "Username already exists");
        }
        // Validate email
        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("email", "Email is required");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new ValidationException("email", "Email is invalid");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ValidationException("email", "Email already exists");
        }
        // Validate password
        if (password == null || password.isEmpty()) {
            throw new ValidationException("password", "Password is required");
        }
        if (password.length() < 6) {
            throw new ValidationException("password", "Password must be at least 6 characters");
        }
        if (!password.matches(".*[A-Za-z].*") || !password.matches(".*[0-9].*")) {
            throw new ValidationException("password", "Password must contain letters and numbers");
        }
        // Validate roles
        if (roleNames == null || roleNames.isEmpty()) {
            throw new ValidationException("roles", "At least one role is required");
        }
        Set<Role> roles = new HashSet<>();
        for (String roleName : roleNames) {
            Role role = roleRepository.findByName(roleName).orElse(null);
            if (role == null) {
                throw new ValidationException("roles", "Role not found: " + roleName);
            }
            roles.add(role);
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}