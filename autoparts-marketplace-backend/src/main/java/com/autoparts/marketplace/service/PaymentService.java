package com.autoparts.marketplace.service;

import com.autoparts.marketplace.dto.PaymentDTO;
import com.autoparts.marketplace.entity.Order;
import com.autoparts.marketplace.entity.Payment;
import com.autoparts.marketplace.repository.OrderRepository;
import com.autoparts.marketplace.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;

    public PaymentDTO pay(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null || order.getPayment() == null) return null;
        Payment payment = order.getPayment();
        payment.setStatus("SUCCESS");
        payment.setTransactionId(UUID.randomUUID().toString());
        payment = paymentRepository.save(payment);
        return toDTO(payment);
    }

    public Optional<PaymentDTO> getPayment(Long paymentId) {
        return paymentRepository.findById(paymentId).map(this::toDTO);
    }

    private PaymentDTO toDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setOrderId(payment.getOrder().getId());
        dto.setStatus(payment.getStatus());
        dto.setTransactionId(payment.getTransactionId());
        return dto;
    }
}
