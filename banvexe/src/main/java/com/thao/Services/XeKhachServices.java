/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.ChuyenXe;
import com.thao.pojo.Ve;
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
    
    public int themXK(XeKhach xk){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "insert into xekhach values(?, ?, ?)";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, xk.getId());
            stml.setInt(2, xk.getSochongoi());
            stml.setString(3, xk.getBienso());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XeKhachServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int suaXK(XeKhach xk){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "update xekhach set sochongoi = ?, bienso = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setInt(1, xk.getSochongoi());
            stml.setString(2, xk.getBienso());
            stml.setString(3, xk.getId());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(XeKhachServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int xoaXK(XeKhach xk){
        int count = 0;
        try(Connection conn = DatabaseConnection.getDBConnection()){
            conn.setAutoCommit(false);
            ChuyenXeServices cxs = new ChuyenXeServices();
            VeServices ves = new VeServices();
            List<ChuyenXe> listCX = cxs.getCXsTheoXK(xk.getId());
            
            String sql = "delete from ghe where xekhach_id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, xk.getId());
            
            count += stml.executeLargeUpdate();
            
            
            
            for(ChuyenXe cx : listCX){
                sql = "delete from ve where chuyenxe_id = ?";
                stml = conn.prepareCall(sql);
                stml.setString(1, cx.getId());
                
                count += stml.executeLargeUpdate();
                
                sql = "delete from chuyenxe where id = ?";
                stml = conn.prepareCall(sql);
                stml.setString(1, cx.getId());
                
                count += stml.executeLargeUpdate();
            }
            
            sql = "delete from xekhach where id = ?";
            stml = conn.prepareCall(sql);
            stml.setString(1, xk.getId());
                
            count += stml.executeUpdate();
            
            conn.commit();
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(XeKhachServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
