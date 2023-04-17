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
import java.sql.Statement;
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
    
    
    public int themUser(User u){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "insert into user values(?,?,?,?,?,?,?)";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, u.getId());
            stml.setString(2, u.getHo());
            stml.setString(3, u.getTen());
            stml.setString(4, u.getSdt());
            stml.setString(5, u.getUsername());
            stml.setString(6, u.getPassword());
            stml.setBoolean(7, u.isAdmin());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int suaUser(User u){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "update user set ho = ?, ten = ?, sdt, = ?, username = ?, password = ?, admin = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, u.getHo());
            stml.setString(2, u.getTen());
            stml.setString(3, u.getSdt());
            stml.setString(4, u.getUsername());
            stml.setString(5, u.getPassword());
            stml.setBoolean(6, u.isAdmin());
            stml.setString(7, u.getId());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int xoaUser(String userID){
        int count = 0;
        try(Connection conn = DatabaseConnection.getDBConnection()){
            conn.setAutoCommit(false);
            String sql = "update ve set user_id = 1";
            Statement stat = conn.createStatement();
            count += stat.executeUpdate(sql);
            
            sql = "delete from user where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, userID);
            
            count += stml.executeUpdate();
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            return count;
        }
    }
}
