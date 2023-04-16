/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.ChuyenXe;
import com.thao.pojo.Ve;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chung Vu
 */
public class ChuyenXeServices {
    public List<ChuyenXe> getChuyenXe() throws SQLException{
        List<ChuyenXe> listCX = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            Statement stat = conn.createStatement();
            
            ResultSet rs = stat.executeQuery("select * from chuyenxe");
//            ByteBuffer buffer;
//
//            String utf8EncodedString;
            while(rs.next()){
//                buffer = StandardCharsets.UTF_8.encode(rs.getString("name")); 
//                utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();
                listCX.add(new ChuyenXe(rs.getString("id"), rs.getString("name"), rs.getTimestamp("ngaykhoihanh").toLocalDateTime(), rs.getDouble("giave"), rs.getString("xekhach_id"), rs.getString("benxedi_id"), rs.getString("benxeden_id")));
            }
        }
        return listCX;
    }
    
    public List<ChuyenXe> getChuyenXe(String kw) throws SQLException {
        List<ChuyenXe> listCX = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "select * from chuyenxe";
            if(kw != null && !kw.isEmpty())
                sql += " WHERE name like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                listCX.add(new ChuyenXe(rs.getString("id"), rs.getString("name"), rs.getTimestamp("ngaykhoihanh").toLocalDateTime(), rs.getDouble("giave"), rs.getString("xekhach_id"), rs.getString("benxedi_id"), rs.getString("benxeden_id")));
            }
        }
        
        return listCX;
    }
    
    public ChuyenXe getCX(String id){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "select * from chuyenxe where id = ?";
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return new ChuyenXe(rs.getString("id"), rs.getString("name"), rs.getTimestamp("ngaykhoihanh").toLocalDateTime(), rs.getDouble("giave"), rs.getString("xekhach_id"), rs.getString("benxedi_id"), rs.getString("benxeden_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ChuyenXe getCXTheoName(String name){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "select * from chuyenxe where name = ? limit 1";
                        PreparedStatement stm = conn.prepareCall(sql);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return new ChuyenXe(rs.getString("id"), rs.getString("name"), rs.getTimestamp("ngaykhoihanh").toLocalDateTime(), rs.getDouble("giave"), rs.getString("xekhach_id"), rs.getString("benxedi_id"), rs.getString("benxeden_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int demVeThuocChuyenXe(ChuyenXe cx, List<Ve> listVe){
        int count = 0;
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "select count(*) as total from Ve where chuyenxe_id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, cx.getId());
            ResultSet rs = stml.executeQuery();
            while(rs.next()){
                count = rs.getInt("total");
            }
            return count + listVe.size();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
    public int themCX(ChuyenXe cx){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "insert into chuyenxe values(?,?,?,?,?,?,?)";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1,cx.getId());
            stml.setString(2, cx.getName());
            stml.setString(3, cx.getNgaykhoihanh().toString());
            stml.setDouble(4, cx.getGiave());
            stml.setString(5, cx.getXekhach_id());
            stml.setString(6, cx.getBenxedi_id());
            stml.setString(7, cx.getBenxeden_id());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int xoaCX(String cxID){
        int count = 0;
        try(Connection conn = DatabaseConnection.getDBConnection()){
            conn.setAutoCommit(false);
            VeServices ves = new VeServices();
            ChuyenXeServices cxs = new ChuyenXeServices();
            List<Ve> listVe = ves.getVeTheoChuyenXe(cxs.getCX(cxID));
            String sql;
            PreparedStatement stml;
            for(Ve ve: listVe){
                sql = "delete from ghe where ve_id = ?";
                stml = conn.prepareCall(sql);
                stml.setString(1, ve.getId());
                
                count += stml.executeUpdate();
            }
            sql = "delete from ve where chuyenxe_id = ?";
            stml = conn.prepareCall(sql);
            stml.setString(1, cxID);
            count += stml.executeUpdate();
            
            sql = "delete from chuyenxethuoctuyenduong where chuyenxe_id = ?";
            stml = conn.prepareCall(sql);
            stml.setString(1, cxID);
            count += stml.executeUpdate();
            
            sql = "delete from chuyenxe where id = ?";
            stml = conn.prepareCall(sql);
            stml.setString(1, cxID);
            count += stml.executeUpdate();
            
            conn.commit();
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeServices.class.getName()).log(Level.SEVERE, null, ex);
            return count;
        }
    }
        
        public int suaCX(ChuyenXe cx){
            try(Connection conn = DatabaseConnection.getDBConnection()){
                String sql = "update chuyenxe set name = ?, ngaykhoihanh = ?, giave = ?, xekhach_id = ?, benxedi_id = ?, benxeden_id = ? where id = ?";
                PreparedStatement stml = conn.prepareCall(sql);
                stml.setString(1, cx.getName());
                stml.setString(2, cx.getNgaykhoihanh().toString());
                stml.setDouble(3, cx.getGiave());
                stml.setString(4, cx.getXekhach_id());
                stml.setString(5, cx.getBenxedi_id());
                stml.setString(6, cx.getBenxeden_id());
                stml.setString(7, cx.getId());
                
                return stml.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(ChuyenXeServices.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
        
    public List<ChuyenXe> getCXsTheoBX(String bxID){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            List<ChuyenXe> listCX = new ArrayList<>();
            String sql = "select * from chuyenxe where benxedi_id = ? or benxeden_id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, bxID);
            stml.setString(2, bxID);
            
            ResultSet rs = stml.executeQuery();
            while(rs.next()){
                listCX.add(new ChuyenXe(rs.getString("id"), rs.getString("name"), rs.getTimestamp("ngaykhoihanh").toLocalDateTime(), rs.getDouble("giave"), rs.getString("xekhach_id"), rs.getString("benxedi_id"), rs.getString("benxeden_id")));
            }
            
            return listCX;
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<ChuyenXe> getCXsTheoXK(String xkID){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            List<ChuyenXe> listCX = new ArrayList<>();
            String sql = "select * from chuyenxe where xekhach_id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, xkID);
            
            ResultSet rs = stml.executeQuery();
            while(rs.next()){
                listCX.add(new ChuyenXe(rs.getString("id"), rs.getString("name"), rs.getTimestamp("ngaykhoihanh").toLocalDateTime(), rs.getDouble("giave"), rs.getString("xekhach_id"), rs.getString("benxedi_id"), rs.getString("benxeden_id")));
            }
            
            return listCX;
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   
//    public static void main(String[] args) throws SQLException{
//        ChuyenXeServices cxs = new ChuyenXeServices();
//        List<ChuyenXe> listcx = cxs.getChuyenXe();
//        for(ChuyenXe cx: listcx){
//            System.out.println(cx.getName());
//        }
//    }
}
