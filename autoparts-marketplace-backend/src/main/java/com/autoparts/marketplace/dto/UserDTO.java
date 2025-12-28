package com.autoparts.marketplace.dto;

import java.util.Set;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserDTO {
    private Long id;
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters")
    private String username;
    private String email;
    private boolean enabled;
    private Set<String> roles;
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> roles) { this.roles = roles; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}