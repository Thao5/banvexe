/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.BenXe;
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
public class KhoaBeoBenXeService {
    public BenXe getBenXe(String id) throws SQLException{
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "SELECT * FROM benxe WHERE id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, id);
            ResultSet rs = stml.executeQuery();
            if(rs.next()){
                return new BenXe(rs.getString("id"), rs.getString("name"), rs.getString("address"));
            }
        } 
        return null;
    }
    public BenXe getBenXeTheoaddress(String adress) throws SQLException{
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "SELECT * FROM benxe WHERE adress = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, adress);
            ResultSet rs = stml.executeQuery();
            if(rs.next()){
                return new BenXe(rs.getString("id"), rs.getString("name"), rs.getString("address"));
            }
        } 
        return null;
    } 
    public List<BenXe> getBenXe() throws SQLException{
        try(Connection conn = DatabaseConnection.getDBConnection()){
            List<BenXe> listBenXe = new ArrayList<>();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM benxe");
            
            while(rs.next()){
                listBenXe.add(new BenXe(rs.getString("id"), rs.getString("name"), rs.getString("address")));
            }
            return listBenXe;
        } 
    }
    public List<BenXe> getLBenXe(String kw) throws SQLException {
        List<BenXe> listBX = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "SELECT * FROM benxe";
            if (kw != null && !kw.isEmpty()) {
                sql += " WHERE name like concat('%', ?, '%')";
            }

            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty()) {
                stm.setString(1, kw);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listBX.add(new BenXe(rs.getString("id"), rs.getString("name"), rs.getString("address")));
            }
        }

        return listBX;
    }
 public boolean insertBenXe(BenXe benxe) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "insert into benxe values(?,?,?)";
            PreparedStatement stml = conn.prepareCall(sql);

            stml.setString(1, benxe.getId());
            stml.setString(2, benxe.getName());
            stml.setString(3, benxe.getAddress());
           

            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }
  public boolean deleteBenXe(String id) throws SQLException {
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            String sql = "DELETE FROM benxe WHERE id=?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);

            return stm.executeUpdate() > 0;
        }
    }

    public boolean updateBenXe(BenXe BenXe) throws SQLException {
        boolean result = false;
        try (Connection conn = DatabaseConnection.getDBConnection()) {
            conn.setAutoCommit(false);

            String sql = "UPDATE BenXe  SET name = ?, address = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, BenXe.getName());
            stml.setString(2, BenXe.getAddress());
           
            stml.setString(3, BenXe.getId());

            int kq = stml.executeUpdate();
            if (kq > 0) {
                conn.commit();
                result = true;
            }
        }
        return result;
    }
}
