/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.ChuyenXeServices;
import com.thao.Services.GheServices;
import com.thao.Services.VeServices;
import com.thao.Services.XeKhachServices;
import com.thao.Utils.MessageBox;
import com.thao.Utils.PrintWord;
import static com.thao.banvexe.FXMLDatVeController.cx;
import static com.thao.banvexe.FXMLDatVeController.listVeDaDat;
import com.thao.pojo.Ghe;
import com.thao.pojo.Ve;
import java.time.LocalDateTime;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author Chung Vu
 */
public class FXMLBanVeController {
    @FXML private TextField txtKH;
    @FXML private TextField txtSDT;
    @FXML private TextField txtSoGhe;
    
    public void banVe(ActionEvent evt){
        VeServices ves = new VeServices();
        ChuyenXeServices cxs = new ChuyenXeServices();
        XeKhachServices xks = new XeKhachServices();
        Ve veBan = new Ve(txtSoGhe.getText(), cx.getGiave(), LocalDateTime.now(), txtKH.getText(), txtSDT.getText(), FXMLDangNhapController.account.getId(), cx.getId());
        if(ves.kiemTraVeMua(veBan, cx)){
            if(ves.isChoTrong(veBan, FXMLDatVeController.listVeDaDat) && cxs.demVeThuocChuyenXe(cx, listVeDaDat) < xks.getXK(cx.getXekhach_id()).getSochongoi()){
                ves.themVe(veBan);
                GheServices ghes = new GheServices();
                ghes.themGhe(new Ghe(veBan.getSoghe(), true, veBan.getId(), cx.getXekhach_id()));
                PrintWord.printWord(veBan, cx, FXMLDangNhapController.account);
            } else {
                Alert error = MessageBox.getBox("Không thể đặt vé!", "Chỗ đã được đặt trước hoặc đã hết chỗ", Alert.AlertType.ERROR);
                error.showAndWait();
            }
        } else {
            Alert error = MessageBox.getBox("Không thể đặt vé!", "đã quá thời gian đặt vé", Alert.AlertType.ERROR);
            error.showAndWait();
        }
    }
}
