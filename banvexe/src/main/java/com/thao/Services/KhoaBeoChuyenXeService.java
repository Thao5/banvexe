/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.ChuyenXe;
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

/**
 *
 * @author anhkh
 */
public class KhoaBeoChuyenXeService {

    public List<ChuyenXe> getChuyenXe() throws SQLException {
        List<ChuyenXe> listchuyenXe = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            Statement stat = conn.createStatement();

            ResultSet rs = stat.executeQuery("SELECT * FROM chuyenxe");

            while (rs.next()) {

                listchuyenXe.add(new ChuyenXe(rs.getString("id"), rs.getString("name"), rs.getTimestamp("ngaykhoihanh").toLocalDateTime(), rs.getDouble("giave"), rs.getString("xekhach_id"), rs.getString("benxedi_id"), rs.getString("benxeden_id")));
            }
        }
        return listchuyenXe;
    }

    public List<ChuyenXe> getChuyenXe(String kw) throws SQLException {
        List<ChuyenXe> listchuyenXe = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "SELECT * FROM chuyenxe";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE name like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listchuyenXe.add(new ChuyenXe(rs.getString("id"), rs.getString("name"), rs.getTimestamp("ngaykhoihanh").toLocalDateTime(), rs.getDouble("giave"), rs.getString("xekhach_id"), rs.getString("benxedi_id"), rs.getString("benxeden_id")));
            }
        }

        return listchuyenXe;
    }

    public ChuyenXe getChuyenXeById(String id) throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "SELECT * FROM chuyenxe WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ChuyenXe cx = new ChuyenXe(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getTimestamp("ngaykhoihanh").toLocalDateTime(),
                        rs.getDouble("giave"),
                        rs.getString("xekhach_id"),
                        rs.getString("benxedi_id"),
                        rs.getString("benxeden_id")
                );
                return cx;
            }
            return null;
        }
    }

    public boolean checkTimeCX(String maChuyenXe) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            // Lấy thông tin về ngày hiện tại
            LocalDateTime now = LocalDateTime.now();

            String sql = "SELECT ngaykhoihanh FROM chuyenxe WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, maChuyenXe);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                LocalDateTime ngayKhoiHanh = rs.getTimestamp("ngaykhoihanh").toLocalDateTime();
                Duration duration = Duration.between(now, ngayKhoiHanh);
                long diffMinutes = duration.toMinutes();
//            return (int) diffMinutes;
                if (diffMinutes < 5) {
                    break;
                } else if (diffMinutes >= 60) {
                    result = true;
                    break;
                }
            }
        }
        return result;

    }

    public boolean insertCx(ChuyenXe chuyenXe) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "insert into chuyenxe values(?,?,?,?,?,?,?)";
            PreparedStatement stml = conn.prepareCall(sql);

            stml.setString(1, chuyenXe.getId());
            stml.setString(2, chuyenXe.getName());
            stml.setString(3, chuyenXe.getNgaykhoihanh().toString());
            stml.setDouble(4, chuyenXe.getGiave());
            stml.setString(5, chuyenXe.getXekhach_id());
            stml.setString(6, chuyenXe.getBenxedi_id());
            stml.setString(7, chuyenXe.getBenxeden_id());

            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }

    public boolean deleteChuyenXe(String id) throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "DELETE FROM Chuyenxe WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);

            return stm.executeUpdate() > 0;
        }
    }

    public boolean updateCx(ChuyenXe chuyenXe) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "UPDATE Chuyenxe  SET name = ?, ngaykhoihanh = ?, giave = ?, xekhach_id = ?, benxedi_id = ?, benxeden_id = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, chuyenXe.getName());
            stml.setString(2, chuyenXe.getNgaykhoihanh().toString());
            stml.setDouble(3, chuyenXe.getGiave());
            stml.setString(4, chuyenXe.getXekhach_id());
            stml.setString(5, chuyenXe.getBenxedi_id());
            stml.setString(6, chuyenXe.getBenxeden_id());
            stml.setString(7, chuyenXe.getId());

            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }
}
