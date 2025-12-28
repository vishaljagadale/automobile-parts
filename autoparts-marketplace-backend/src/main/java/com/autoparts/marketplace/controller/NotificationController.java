package com.autoparts.marketplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autoparts.marketplace.service.NotificationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/email")
    public ResponseEntity<String> sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        boolean sent = notificationService.sendEmail(to, subject, body);
        if (sent) return ResponseEntity.ok("Email sent successfully");
        return ResponseEntity.status(500).body("Failed to send email");
    }
}