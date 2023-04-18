/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.TuyenDuong;
import com.thao.pojo.XeKhach;
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
 * @author anhkh
 */
public class KhoaBeoTuyenduongService {
     public List<TuyenDuong> getTuyenDuong() throws SQLException{
        List<TuyenDuong> ListTuyenDuong = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM tuyenduong");
            while(rs.next()){
                ListTuyenDuong.add(new TuyenDuong(rs.getString("id"), rs.getString("diemdi"), rs.getString("diemden")));
            }
            return ListTuyenDuong;
        } 
    }
     public List<TuyenDuong> getLTuyenDuong(String kw) throws SQLException {
        List<TuyenDuong> ListTuyenDuong = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "SELECT * FROM tuyenduong";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE diemdi like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                 ListTuyenDuong.add(new TuyenDuong(rs.getString("id"), rs.getString("diemdi"), rs.getString("diemden")));
            }
        }
        return ListTuyenDuong;
    }
      public boolean insertTuyenDuong(TuyenDuong TuyenDuong) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "insert into Tuyenduong values(?,?,?)";
            PreparedStatement stml = conn.prepareCall(sql);

            stml.setString(1, TuyenDuong.getDiemdi());
            stml.setString(2, TuyenDuong.getDiemden());
          
            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }
  public boolean deleteTuyenduong(String id) throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "DELETE FROM Tuyenduong WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);

            return stm.executeUpdate() > 0;
        }
    }

    public boolean updateTuyenDuong(TuyenDuong TuyenDuong) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "UPDATE Tuyenduong  SET diemdi = ?, diemden = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, TuyenDuong.getDiemdi());
            stml.setString(2, TuyenDuong.getDiemden());
           
            stml.setString(3, TuyenDuong.getId());

            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }      
}
