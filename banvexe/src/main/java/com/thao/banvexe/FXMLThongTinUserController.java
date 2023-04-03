/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.VeServices;
import com.thao.Utils.MessageBox;
import static com.thao.banvexe.FXMLDatVeController.cx;
import static com.thao.banvexe.FXMLDatVeController.listVeDaDat;
import com.thao.pojo.Ve;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 *
 * @author Chung Vu
 */
public class FXMLThongTinUserController {
    @FXML private TextField thongTinKH;
    @FXML private TextField sdt;
    @FXML private TextField soGhe;
    public void datVe(ActionEvent evt){
        Alert confirm = MessageBox.getBox("Confirm", "Bạn có muốn đặt vé?", Alert.AlertType.CONFIRMATION);
        confirm.showAndWait().ifPresent(res -> {
            if(res == ButtonType.OK){
                VeServices ves = new VeServices();
                Ve ve = new Ve(soGhe.getText(), cx.getGiave(), LocalDateTime.now(), thongTinKH.getText(), sdt.getText(), FXMLDangNhapController.account.getId(), cx.getId()); 
                if(ves.isChoTrong(ve, listVeDaDat)){
                    
                    if(ves.kiemTraVeDat(ve, cx))
                        listVeDaDat.add(ve);
                    else {
                        Alert error = MessageBox.getBox("Không thể đặt vé!", "đã quá thời gian đặt vé", Alert.AlertType.ERROR);
                        error.showAndWait();
                    }
                } else {
                    Alert error = MessageBox.getBox("Không thể đặt vé!", "Chỗ đã được đặt trước", Alert.AlertType.ERROR);
                        error.showAndWait();
                }
            }
        });
    }
}
