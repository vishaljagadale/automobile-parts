package com.autoparts.marketplace.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;

@Service
public class AnalyticsService {
    public Map<String, Object> getSalesAnalytics() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalSales", 1200);
        data.put("monthlySales", 100);
        return data;
    }
    public Map<String, Object> getTopProducts() {
        Map<String, Object> data = new HashMap<>();
        data.put("topProducts", new String[]{"Brake Pad", "Clutch Plate"});
        return data;
    }
    public Map<String, Object> getRevenueAnalytics() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalRevenue", 500000);
        data.put("monthlyRevenue", 40000);
        return data;
    }
    public Map<String, Object> getReturnsAnalytics() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalReturns", 20);
        data.put("monthlyReturns", 2);
        return data;
    }
}
