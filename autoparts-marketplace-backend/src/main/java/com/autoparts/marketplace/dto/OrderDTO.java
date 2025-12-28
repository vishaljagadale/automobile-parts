package com.autoparts.marketplace.dto;

import java.time.LocalDateTime;
import java.util.Set;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public class OrderDTO {
    private Long id;
    @NotNull(message = "User ID is required")
    private Long userId;
    @NotNull(message = "Order items are required")
    @Size(min = 1, message = "Order must have at least one item")
    private Set<OrderItemDTO> items;
    @NotBlank(message = "Order status is required")
    private String status;
    @NotNull(message = "Order date is required")
    private LocalDateTime orderDate;
    @NotNull(message = "Payment details are required")
    private PaymentDTO payment;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Set<OrderItemDTO> getItems() { return items; }
    public void setItems(Set<OrderItemDTO> items) { this.items = items; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public PaymentDTO getPayment() { return payment; }
    public void setPayment(PaymentDTO payment) { this.payment = payment; }
}