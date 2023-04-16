/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.Ghe;
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
public class GheServices {
    public int themGhe(Ghe ghe){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            conn.setAutoCommit(false);
            String sql = "insert into ghe values(?, ?, ?, ?, ?)";
            PreparedStatement stat = conn.prepareCall(sql);
            
            stat.setString(1, ghe.getId());
            stat.setString(2, ghe.getName());
            stat.setBoolean(3, ghe.isTrangthai());
            stat.setString(4, ghe.getVe_id());
            stat.setString(5, ghe.getXekhach_id());
            int result = stat.executeUpdate();
            conn.commit();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(GheServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public List<Ghe> getGhes(){
        List<Ghe> listGhe = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "select * from ghe";
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                listGhe.add(new Ghe(rs.getString("id"), rs.getString("name"), rs.getBoolean("trangthai"), rs.getString("ve_id"), rs.getString("xekhach_id")));
            }
            return listGhe;
        } catch (SQLException ex) {
            Logger.getLogger(GheServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int suaGhe(Ghe ghe){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "update ghe set name = ?, trangthai = ?, ve_id = ?, xekhach_id = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, ghe.getName());
            stml.setBoolean(2, ghe.isTrangthai());
            stml.setString(3, ghe.getVe_id());
            stml.setString(4, ghe.getXekhach_id());
            stml.setString(5, ghe.getId());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GheServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int xoaGhe(String id){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "delete ghe where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, id);
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GheServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
