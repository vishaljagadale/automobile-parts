package com.autoparts.marketplace.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public boolean sendEmail(String to, String subject, String body) {
        // Mock email sending logic
        System.out.println("Sending email to: " + to + " | Subject: " + subject + " | Body: " + body);
        return true;
    }
}
