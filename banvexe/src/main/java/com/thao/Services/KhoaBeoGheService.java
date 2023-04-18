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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * //
 *
 *
 * @author anhkh
 */
public class KhoaBeoGheService {

    public List<Ghe> getListGhe(String kw) throws SQLException {

        List<Ghe> gheList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "select * from ghe";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE name like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                gheList.add(new Ghe(rs.getString("id"), rs.getString("name"), rs.getBoolean("trangthai"), rs.getString("ve_id"), rs.getString("xekhach_id")));
            }
        }

        return gheList;
    }

    public boolean updateGhe(String id, String name) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);
            String sqlUpdate = "UPDATE ghe SET trangthai = ?, ve_id = ? WHERE name = ?";
            PreparedStatement stmtUpdateGhe = conn.prepareStatement(sqlUpdate);
            stmtUpdateGhe.setBoolean(1, true);
            stmtUpdateGhe.setString(2, id);
            stmtUpdateGhe.setString(3, name);

            int kq = stmtUpdateGhe.executeUpdate();
            if (kq > -0) {
                conn.commit();
            }
            result = true;

        }
        return result;
    }

    public boolean updateGheEmpty(String id, String name) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);
            String sqlUpdate = "UPDATE ghe SET trangthai = ?, ve_id = ? WHERE name = ?";
            PreparedStatement stmtUpdateGhe = conn.prepareStatement(sqlUpdate);
            stmtUpdateGhe.setBoolean(1, false);
            stmtUpdateGhe.setString(2, null);
            stmtUpdateGhe.setString(3, name);
            int kq = stmtUpdateGhe.executeUpdate();
            if (kq > -0) {
                conn.commit();
            }
            result = true;

        }
        return result;
    }
public boolean updateGheEmpty2(Ghe ghe) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);
         
             String sql = "UPDATE Ghe  SET name = ?, trangthai = ?, ve_id = ?, xekhach_id = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, ghe.getName());
            stml.setBoolean(2, false);
            stml.setString(3, null);
            stml.setString(4, ghe.getXekhach_id());
            stml.setString(5, ghe.getId());
            int kq = stml.executeUpdate();
            if (kq > -0) {
                conn.commit();
            }
            result = true;

        }
        return result;
    }

    public boolean checkGheTrong(String tenGhe) throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection()) {
   
            String sql = "SELECT * FROM Ghe WHERE name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tenGhe);
            ResultSet rs = stmt.executeQuery();

       
            while (rs.next()) {
                boolean trangthai = rs.getBoolean("trangthai");
                String ve_id = rs.getString("ve_id");

                if (trangthai == false && ve_id == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean insertGhe(Ghe ghe) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO ghe VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, ghe.getId());
            stml.setString(2, ghe.getName());
            stml.setBoolean(3,false);
           stml.setString(4, null);
            stml.setString(5, ghe.getXekhach_id());
           

            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }

    public boolean deleteGhe(String id) throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "DELETE FROM ghe WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);

            return stm.executeUpdate() > 0;
        }
    }

    public boolean updateGhe(Ghe ghe) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "UPDATE Ghe  SET name = ?, trangthai = ?, ve_id = ?, xekhach_id = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, ghe.getName());
            stml.setBoolean(2, ghe.isTrangthai());
            stml.setString(3, ghe.getVe_id());
            stml.setString(4, ghe.getXekhach_id());
            stml.setString(5, ghe.getId());
            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }
}
