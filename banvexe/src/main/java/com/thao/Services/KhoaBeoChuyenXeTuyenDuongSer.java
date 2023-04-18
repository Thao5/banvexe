/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.ChuyenXeThuocTuyenDuong;
import com.thao.pojo.TuyenDuong;
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
public class KhoaBeoChuyenXeTuyenDuongSer {
    public List<ChuyenXeThuocTuyenDuong> getChuyenXeTuyenDuong() throws SQLException{
        List<ChuyenXeThuocTuyenDuong> ListChuyenXeTuyenDuong = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM chuyenxethuoctuyenduong");
            while(rs.next()){
                ListChuyenXeTuyenDuong.add(new ChuyenXeThuocTuyenDuong(rs.getString("id"), rs.getString("chuyenxe_id"), rs.getString("tuyenduong_id")));
            }
            return ListChuyenXeTuyenDuong;
        } 
}
 public List<ChuyenXeThuocTuyenDuong> getCXTuyenDuong(String kw) throws SQLException {
        List<ChuyenXeThuocTuyenDuong> CXTD = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "SELECT * FROM ChuyenXeThuocTuyenDuong";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE chuyenxe_id like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                CXTD.add(new ChuyenXeThuocTuyenDuong(rs.getString("id"), rs.getString("chuyenxe_id"), rs.getString("tuyenduong_id")));
            }
        }
        return CXTD;
    }
     public boolean insertChuyenXeThuocTuyenDuong(ChuyenXeThuocTuyenDuong cxtd) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "insert into ChuyenXeThuocTuyenDuong values(?,?,?)";
            PreparedStatement stml = conn.prepareCall(sql);
             stml.setString(1, cxtd.getId());
            stml.setString(2, cxtd.getChuyenxe_id());
            stml.setString(3, cxtd.getTuyenduong_id());
          
            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }
  public boolean deleteChuyenXeTuyenDuong(String id) throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "DELETE FROM ChuyenXeThuocTuyenDuong WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);

            return stm.executeUpdate() > 0;
        }
    }

    public boolean updateChuyenXeTuyenDuong(ChuyenXeThuocTuyenDuong cxtd) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "UPDATE ChuyenXeThuocTuyenDuong  SET chuyenxe_id = ?, tuyenduong_id = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, cxtd.getChuyenxe_id());
            stml.setString(2, cxtd.getTuyenduong_id());
            stml.setString(3, cxtd.getId());

            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }      
}
