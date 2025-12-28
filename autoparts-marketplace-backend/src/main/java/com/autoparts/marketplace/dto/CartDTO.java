package com.autoparts.marketplace.dto;

import java.util.Set;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CartDTO {
    private Long id;
    @NotNull(message = "User ID is required")
    private Long userId;
    @NotNull(message = "Cart items are required")
    @Size(min = 1, message = "Cart must have at least one item")
    private Set<CartItemDTO> items;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Set<CartItemDTO> getItems() { return items; }
    public void setItems(Set<CartItemDTO> items) { this.items = items; }
}