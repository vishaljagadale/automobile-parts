package com.autoparts.marketplace.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class PaymentDTO {
    private Long id;
    @NotNull(message = "Order ID is required")
    private Long orderId;
    @NotBlank(message = "Payment status is required")
    private String status;
    @NotBlank(message = "Transaction ID is required")
    private String transactionId;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
}