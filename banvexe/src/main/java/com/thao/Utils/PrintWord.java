/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Utils;

import com.thao.Services.BenXeServices;
import com.thao.Services.ChuyenXeServices;
import com.thao.Services.UserServices;
import com.thao.Services.VeServices;
import com.thao.Services.XeKhachServices;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.User;
import com.thao.pojo.Ve;
import com.thao.pojo.XeKhach;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import org.apache.poi.xwpf.usermodel.XWPFRun;


/**
 *
 * @author Chung Vu
 */
public class PrintWord {
        
//    public static boolean printWord(Ve ve, ChuyenXe cx, User u){
//        BenXeServices bxs = new BenXeServices();
//        ChuyenXeServices cxs = new ChuyenXeServices();
//        UserServices urs = new UserServices();
//        VeServices ves = new VeServices();
//        XeKhachServices xks = new XeKhachServices();
//        XWPFDocument document = new XWPFDocument();
//        XWPFParagraph titleGraph = document.createParagraph();
//        titleGraph.setAlignment(ParagraphAlignment.CENTER);
//        String title = "Thẻ lên xe";
//        XWPFRun titleRun = titleGraph.createRun();
//        titleRun.setBold(true);
//        titleRun.setText(title);
//        
//        XWPFParagraph para1 = document.createParagraph();
//        para1.setAlignment(ParagraphAlignment.CENTER);
//        String dataPara1 = String.format("%s => %s\n",bxs.getBX(cx.getBenxedi_id()).getAddress(), bxs.getBX(cx.getBenxeden_id()).getAddress());
//        dataPara1 += String.format("Xe: %s", xks.getXK(cx.getXekhach_id()).getBienso());
//        XWPFRun para1Run = para1.createRun();             
//        para1Run.setText(dataPara1);
//        
//        XWPFParagraph para2 = document.createParagraph();
//        para2.setAlignment(ParagraphAlignment.LEFT);
//        String dataPara2 = String.format("Nơi đi: %s\n", bxs.getBX(cx.getBenxedi_id()).getAddress());
//        dataPara2 += String.format("Nơi đến: %s\n", bxs.getBX(cx.getBenxeden_id()).getAddress());
//        dataPara2 += String.format("Khởi hành: %s\n", cx.getNgaykhoihanh().toString());
//        dataPara2 += String.format("Ghế: %s\n", ve.getSoghe());
//        dataPara2 += String.format("Giá vé: %s\n", ve.getGiave());
//        dataPara2 += String.format("SDT: %s\n", ve.getSdt());
//        dataPara2 += String.format("Nhân Viên: %s\n", u.getTen());
//        dataPara2 += String.format("Ngày In: %s\n", ve.getNgayin().toString());
//        XWPFRun para2Run = para2.createRun();             
//        para2Run.setText(dataPara2);
//        
//        FileOutputStream out;
//        try {
//            out = new FileOutputStream("src/main/resources/word/hoadon.docx");
//            document.write(out);
//
//            out.close();
//
//            document.close();
//            return true;
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(PrintWord.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        } catch (IOException ex) {
//            Logger.getLogger(PrintWord.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }    
//    }
    
    public static boolean printWord(Ve ve, ChuyenXe cx, User u){
        BenXeServices bxs = new BenXeServices();
        ChuyenXeServices cxs = new ChuyenXeServices();
        UserServices urs = new UserServices();
        VeServices ves = new VeServices();
        XeKhachServices xks = new XeKhachServices();
        
        PDDocument
    }
}
