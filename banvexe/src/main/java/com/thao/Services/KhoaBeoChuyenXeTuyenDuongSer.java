/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.ChuyenXeThuocTuyenDuong;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author anhkh
 */
public class KhoaBeoChuyenXeTuyenDuongSer {
    public List<ChuyenXeThuocTuyenDuong> getChuyenXeTuyenDuong() throws SQLException{
        List<ChuyenXeThuocTuyenDuong> ListChuyenXeTuyenDuong = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM chuyenxethuoctuyenduong");
            while(rs.next()){
                ListChuyenXeTuyenDuong.add(new ChuyenXeThuocTuyenDuong(rs.getString("id"), rs.getString("chuyenxe_id"), rs.getString("tuyenduong_id")));
            }
            return ListChuyenXeTuyenDuong;
        } 
}
}
