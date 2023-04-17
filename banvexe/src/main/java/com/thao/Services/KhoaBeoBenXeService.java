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
    

}
