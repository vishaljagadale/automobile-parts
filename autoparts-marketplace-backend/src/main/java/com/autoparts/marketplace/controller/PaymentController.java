package com.autoparts.marketplace.controller;

import com.autoparts.marketplace.dto.PaymentDTO;
import com.autoparts.marketplace.service.PaymentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public ResponseEntity<PaymentDTO> pay(@RequestBody @Valid PaymentDTO paymentDTO) {
        PaymentDTO payment = paymentService.pay(paymentDTO.getOrderId());
        if (payment == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long paymentId) {
        return paymentService.getPayment(paymentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}