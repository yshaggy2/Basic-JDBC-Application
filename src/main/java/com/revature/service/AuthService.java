package com.revature.service;

import com.revature.model.LoginDTO;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {

    public String login(LoginDTO lDTO) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM execs WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, lDTO.getUsername());
            ps.setString(2, lDTO.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "Authentication successful for " + rs.getString("username");
            } else {
                return "Username or Password not found.";
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception caught.");
        }

        return "Login failed.";
    }
    public String register(LoginDTO lDTO) {
        //String sql2 = "INSERT into admins (username, password) VALUES (?,?)";
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM execs WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, lDTO.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return "User already exists!";
            } else {
                //System.out.println("Reached else statement") --> true
                String sql2 = "INSERT into execs (username, password) VALUES (?,?)";
                try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                    ps2.setString(1, lDTO.getUsername());
                    ps2.setString(2, lDTO.getPassword());
                    ps2.executeUpdate();
                    return "Registration for user: " + lDTO.getUsername() + " successful.";
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception caught.");
            e.printStackTrace();
        }
        return "Registration Failed.";
    }
}
