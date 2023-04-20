/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.BenXe;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.Ve;
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
    
    public List<BenXe> getBXs(){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            List<BenXe> listBX = new ArrayList<>();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from benxe");
            
            while(rs.next()){
                listBX.add(new BenXe(rs.getString("id"), rs.getString("name"), rs.getString("address")));
            }
            return listBX;
        } catch (SQLException ex) {
            Logger.getLogger(BenXeServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int themBX(BenXe bx){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "insert into benxe values(?,?,?)";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, bx.getId());
            stml.setString(2, bx.getName());
            stml.setString(3, bx.getAddress());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BenXeServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int suaBX(BenXe bx){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "update benxe set name = ?, address = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, bx.getName());
            stml.setString(2, bx.getAddress());
            stml.setString(3, bx.getId());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BenXeServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int xoaBX(BenXe bx){
        int count = 0;
        try(Connection conn = DatabaseConnection.getDBConnection()){
            conn.setAutoCommit(false);
            ChuyenXeServices cxs = new ChuyenXeServices();
            VeServices ves = new VeServices();
            List<ChuyenXe> listCX = cxs.getCXsTheoBX(bx.getId());
            List<Ve> listVe = new ArrayList<>();
            for(ChuyenXe cx : listCX){
                listVe.addAll(ves.getVeTheoChuyenXe(cx));
            }
            String sql;
            PreparedStatement stml;
            for(Ve ve : listVe){
                sql = "delete from ghe where ve_id = ?";
                stml = conn.prepareCall(sql);
                stml.setString(1, ve.getId());
                
                count += stml.executeLargeUpdate();
                
                sql = "delete from ve where id = ?";
                stml = conn.prepareCall(sql);
                stml.setString(1, ve.getId());
                
                count += stml.executeLargeUpdate();
            }
            
            for(ChuyenXe cx : listCX){
                sql = "delete from chuyenxethuoctuyenduong where chuyenxe_id = ?";
                stml = conn.prepareCall(sql);
                stml.setString(1, cx.getId());
                count += stml.executeLargeUpdate();
                
                sql = "delete from chuyenxe where id = ?";
                stml = conn.prepareCall(sql);
                stml.setString(1, cx.getId());
                count += stml.executeLargeUpdate();
            }
            
            sql = "delete from benxe where id = ?";
            stml = conn.prepareCall(sql);
            stml.setString(1, bx.getId());
            count += stml.executeUpdate();
            
            conn.commit();
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(BenXeServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
