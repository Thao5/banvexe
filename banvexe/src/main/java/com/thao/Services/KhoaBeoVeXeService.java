/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.Utils.MessageBox;
import com.thao.pojo.Ve;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author anhkh
 */
public class KhoaBeoVeXeService {

    public List<Ve> getVe() throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            List<Ve> tks = new ArrayList();

            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from ve");

            while (rs.next()) {
                tks.add(new Ve(rs.getString("id"), rs.getString("soghe"), rs.getDouble("giave"), rs.getTimestamp("ngayin").toLocalDateTime(), rs.getString("khachhang"), rs.getString("sdt"), rs.getString("user_id"), rs.getString("chuyenxe_id")));

            }
            return tks;
        }
    }

    public List<Ve> getListVe(String kw) throws SQLException {
        List<Ve> results = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getDBConnection()) {
           String sql = "SELECT * FROM ve WHERE DATE(ngayin) >= DATE_SUB(DATE(NOW()), INTERVAL 3 DAY)";
            if (kw != null && !kw.isEmpty()) {
                sql += " AND id LIKE CONCAT('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                results.add(new Ve(rs.getString("id"), rs.getString("soghe"), rs.getDouble("giave"), rs.getTimestamp("ngayin").toLocalDateTime(), rs.getString("khachhang"), rs.getString("sdt"), rs.getString("user_id"), rs.getString("chuyenxe_id")));
            }
        }
        return results;
    }

    public boolean deleteVe(String id) throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection()) {

            // Tắt check Khóa ngoại
            Statement disableFKChecks = conn.createStatement();
            disableFKChecks.execute("SET foreign_key_checks = 0");

            ///Gán khóa ngoại  trong bảng ghê bằng 1 null
            String updateChildSql = "UPDATE Ghe SET ve_id=null,trangthai=0 WHERE ve_id=?";
            PreparedStatement updateChildStm = conn.prepareCall(updateChildSql);
            updateChildStm.setString(1, id);
            updateChildStm.executeUpdate();

            // Xóa bản ghi vé
            String deleteParentSql = "DELETE FROM Ve WHERE id=?";
            PreparedStatement deleteParentStm = conn.prepareCall(deleteParentSql);
            deleteParentStm.setString(1, id);
            boolean result = deleteParentStm.executeUpdate() > 0;

            // Bật lại check
            Statement enableFKChecks = conn.createStatement();
            enableFKChecks.execute("SET foreign_key_checks = 1");

            return result;
        }
    }
public Ve getVeById(String id) throws SQLException {
    try (Connection conn = DatabaseConnection.getDBConnection()) {
        String sql = "SELECT * FROM Ve WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Ve v = new Ve();
            v.setId(rs.getString("id"));
            v.setSoghe(rs.getString("soghe"));
            v.setChuyenxe_id(rs.getString("chuyenxe_id"));
            return v;
        }
        return null;
    }
}
public boolean updateVe(String tenGhe, String chuyenxe, String id) throws SQLException {
        boolean result = false;
         KhoaBeoGheService g = new KhoaBeoGheService();
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);
            if (g.checkGheTrong(tenGhe)) {
                String sqlUpdate = "UPDATE Ve SET soghe = ?, chuyenxe_id = ? WHERE id = ?";
                PreparedStatement stmtUpdateVe = conn.prepareStatement(sqlUpdate);
                stmtUpdateVe.setString(1, tenGhe);
                stmtUpdateVe.setString(2, chuyenxe);
                stmtUpdateVe.setString(3, id);
                int kq = stmtUpdateVe.executeUpdate();
                if(kq>0)
                    conn.commit();
                    result=true;
            }
        }
        return result;
    }
     public Boolean InsertVe(Ve ve) throws SQLException{
        try(Connection conn = DatabaseConnection.getDBConnection()){
            boolean result = false;
            conn.setAutoCommit(false);
            String sql = "INSERT INTO VE VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, ve.getId());
            stml.setString(2, ve.getSoghe());
            stml.setDouble(3, ve.getGiave());
            stml.setObject(4, LocalDateTime.now());
            stml.setString(5, ve.getKhachhang());
            stml.setString(6, ve.getSdt());
            stml.setString(7, ve.getUser_id());
            stml.setString(8, ve.getChuyenxe_id());
            
             int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
            conn.commit();
            return result;
        }
    }

}
