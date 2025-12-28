package com.autoparts.marketplace.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autoparts.marketplace.dto.UserDTO;
import com.autoparts.marketplace.entity.User;
import com.autoparts.marketplace.service.AuthService;
import com.autoparts.marketplace.service.JwtUtil;
import com.autoparts.marketplace.exception.ValidationException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) {
        try {
            User user = authService.registerUser(
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getRoles()
            );
            return ResponseEntity.ok("User registered successfully");
        } catch (ValidationException ve) {
            return ResponseEntity.badRequest().body(Map.of(
                "field", ve.getField(),
                "message", ve.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "field", "general",
                "message", "Registration failed: " + e.getMessage()
            ));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDTO userDTO) {
        User user = authService.findByUsername(userDTO.getUsername()).orElse(null);
        if (user != null && passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername());
            String role = user.getRoles().iterator().next().getName(); // get first role
            return ResponseEntity.ok(Map.of(
                "token", token,
                "role", role
            ));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}