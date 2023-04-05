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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chung Vu
 */
public class BenXeServices {
    public BenXe getBX(String id){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "select * from benxe where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, id);
            ResultSet rs = stml.executeQuery();
            if(rs.next()){
                return new BenXe(rs.getString("id"), rs.getString("name"), rs.getString("address"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BenXeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
