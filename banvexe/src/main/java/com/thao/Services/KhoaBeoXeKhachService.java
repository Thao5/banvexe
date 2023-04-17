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
    public XeKhach getXeKhach(String id) throws SQLException{
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "SELECT * FROM xekhach WHERE id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, id);
            ResultSet rs = stml.executeQuery();
            if(rs.next()){
                return new XeKhach(rs.getString("id"), rs.getInt("sochongoi"), rs.getString("bienso"));
            }
        } 
        return null;
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
}
