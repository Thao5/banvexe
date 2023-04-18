/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anhkh
 */
public class KhoaBeoUserService {

    public List<User> getListUser(String Username) throws SQLException {
        List<User> ListUser = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "SELECT * FROM user";
            if (Username != null && !Username.isEmpty()) {
                sql += " WHERE Username like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareCall(sql);
            if (Username != null && !Username.isEmpty()) {
                stm.setString(1, Username);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ListUser.add(new User(rs.getString("id"), rs.getString("ho"), rs.getString("ten"), rs.getString("sdt"), rs.getString("username"), rs.getString("password"), rs.getBoolean("admin")));
            }
        }
        return ListUser;
    }

    public boolean insertUser(User user) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, user.getId());
            stml.setString(2, user.getHo());
            stml.setString(3, user.getTen());
            stml.setString(4, user.getSdt());
            stml.setString(5, user.getUsername());
            stml.setString(6, user.getPassword());
            stml.setBoolean(7, user.isAdmin());
            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }

    public boolean deleteUser(String id) throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "DELETE FROM User WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);

            return stm.executeUpdate() > 0;
        }
    }

    public boolean updateUser(User user) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "UPDATE user SET ho = ?, ten = ?, sdt = ?, username = ?, password = ?, admin = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
           
            stml.setString(1, user.getHo());
            stml.setString(2, user.getTen());
            stml.setString(3, user.getSdt());
            stml.setString(4, user.getUsername());
            stml.setString(5, user.getPassword());
            stml.setBoolean(6, user.isAdmin());
            stml.setString(7, user.getId());

            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }
}
