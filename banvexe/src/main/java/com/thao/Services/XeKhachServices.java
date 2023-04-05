/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.XeKhach;
import java.io.DataInputStream;
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
public class XeKhachServices {
    public List<XeKhach> getXeKhachs() throws SQLException{
        List<XeKhach> listXK = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            Statement stat = conn.createStatement();
            
            ResultSet rs = stat.executeQuery("select * from xekhach");
            while(rs.next()){
                listXK.add(new XeKhach(rs.getString("id"), rs.getInt("sochongoi"), rs.getString("bienso")));
            }
        }
        
        
        return listXK;
    }
    
    public XeKhach getXK(String id){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "select * from xekhach where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, id);
            ResultSet rs = stml.executeQuery();
            if(rs.next()){
                return new XeKhach(rs.getString("id"), rs.getInt("sochongoi"), rs.getString("bienso"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(XeKhachServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
