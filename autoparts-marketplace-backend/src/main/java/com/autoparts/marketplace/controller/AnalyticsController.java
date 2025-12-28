package com.autoparts.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autoparts.marketplace.service.AnalyticsService;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/analytics")
public class AnalyticsController {
    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/sales")
    public ResponseEntity<Map<String, Object>> getSalesAnalytics() {
        return ResponseEntity.ok(analyticsService.getSalesAnalytics());
    }

    @GetMapping("/top-products")
    public ResponseEntity<Map<String, Object>> getTopProducts() {
        return ResponseEntity.ok(analyticsService.getTopProducts());
    }

    @GetMapping("/revenue")
    public ResponseEntity<Map<String, Object>> getRevenueAnalytics() {
        return ResponseEntity.ok(analyticsService.getRevenueAnalytics());
    }

    @GetMapping("/returns")
    public ResponseEntity<Map<String, Object>> getReturnsAnalytics() {
        return ResponseEntity.ok(analyticsService.getReturnsAnalytics());
    }
}