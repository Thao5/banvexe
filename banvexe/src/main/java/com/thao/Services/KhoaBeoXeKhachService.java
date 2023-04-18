/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.BenXe;
import com.thao.pojo.XeKhach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anhkh
 */
public class KhoaBeoXeKhachService {
    public List<XeKhach> getXeKhach(String kw) throws SQLException {
         List<XeKhach> ListXeKhach = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "SELECT * FROM xekhach";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE bienso like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ListXeKhach.add(new XeKhach(rs.getString("id"), rs.getInt("sochongoi"), rs.getString("bienso")));
        }

        return ListXeKhach;
    }
    }    
   
     public XeKhach getXeKhachTheoBs(String bs) throws SQLException{
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "SELECT * FROM xekhach WHERE bienso = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, bs);
            ResultSet rs = stml.executeQuery();
            if(rs.next()){
                return new XeKhach(rs.getString("id"), rs.getInt("sochongoi"), rs.getString("bienso"));
            }
        } 
        return null;
    }
    public List<XeKhach> getXeKhach() throws SQLException{
        try(Connection conn = DatabaseConnection.getDBConnection()){
            List<XeKhach> ListXeKhach = new ArrayList<>();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM xekhach");
            
            while(rs.next()){
                ListXeKhach.add(new XeKhach(rs.getString("id"), rs.getInt("sochongoi"), rs.getString("bienso")));
            }
            return ListXeKhach;
        } 
}
    public boolean insertXeKhach(XeKhach XeKhach) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "insert into xekhach values(?,?,?)";
            PreparedStatement stml = conn.prepareCall(sql);

            stml.setString(1, XeKhach.getId());
            stml.setInt(2, XeKhach.getSochongoi());
            stml.setString(3, XeKhach.getBienso());
           

            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }
  public boolean deleteXeKhach(String id) throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "DELETE FROM XeKhach WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);

            return stm.executeUpdate() > 0;
        }
    }

    public boolean updateXeKhach(XeKhach XeKhach) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "UPDATE XeKhach  SET sochongoi = ?, Bienso = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setInt(1, XeKhach.getSochongoi());
            stml.setString(2, XeKhach.getBienso());
           
            stml.setString(3, XeKhach.getId());

            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }
}

