package com.autoparts.marketplace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SupabaseConnectionTest {
    public static void main(String[] args) {
        // Replace these values with your Supabase credentials
        String url = "jdbc:postgresql://db.idztikosoczyqitlhpkh.supabase.co:5432/postgres";
        String user = "postgres";
        String password = "Akshay@24051995";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Connection to Supabase PostgreSQL successful!");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
