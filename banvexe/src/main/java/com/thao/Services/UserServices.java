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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chung Vu
 */
public class UserServices {
    public User getUser(String username, String password){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "select * from user where username = ? && password = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, username);
            stml.setString(2, password);
            
            ResultSet rs = stml.executeQuery();
            if(rs.next())
                return new User(rs.getString("id"), rs.getString("ho"), rs.getString("ten"), rs.getString("sdt"), rs.getString("username"), rs.getString("password"), rs.getBoolean("admin"));
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
