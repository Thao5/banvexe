/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.ChuyenXeServices;
import com.thao.Services.VeServices;
import com.thao.Utils.MessageBox;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.Ve;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author Chung Vu
 */
public class FXMLDoiVeController {
    @FXML private TextField txtGhe;
    @FXML private TextField txtChuyenXe;
    
    public void doiVe(ActionEvent evt){
        if(txtGhe.getText() != null && !txtGhe.getText().isEmpty() && txtChuyenXe.getText() != null && !txtChuyenXe.getText().isEmpty()){
            
            ChuyenXeServices cxs = new ChuyenXeServices();
            ChuyenXe cx1 = cxs.getCXTheoName(txtChuyenXe.getText());
            VeServices ves = new VeServices();
            Ve tmp = FXMLDatVeController.v;
            tmp.setChuyenxe_id(cx1.getId());
            if(ves.kiemTraVeDat(tmp, cx1)){
                if(ves.isChoTrong(txtGhe.getText(), tmp.getChuyenxe_id(), FXMLDatVeController.listVeDaDat)){
                    FXMLDatVeController.listVeDaDat.get(FXMLDatVeController.listVeDaDat.indexOf(FXMLDatVeController.v)).setSoghe(txtGhe.getText());
                    FXMLDatVeController.listVeDaDat.get(FXMLDatVeController.listVeDaDat.indexOf(FXMLDatVeController.v)).setChuyenxe_id(cx1.getId());
                }
                else {
                    Alert err = MessageBox.getBox("Thông báo!", "Chỗ đã được đặt trước", Alert.AlertType.ERROR);
                    err.showAndWait();
                }
            }
            else{
                Alert error = MessageBox.getBox("Lỗi!", "Đã quá hạn đổi vé", Alert.AlertType.ERROR);
                error.showAndWait();
            }
        }
        else if (txtGhe.getText() != null && !txtGhe.getText().isEmpty()){
            VeServices ves = new VeServices();
            if(ves.isChoTrong(txtGhe.getText(), FXMLDatVeController.v.getChuyenxe_id(), FXMLDatVeController.listVeDaDat)){
                FXMLDatVeController.listVeDaDat.get(FXMLDatVeController.listVeDaDat.indexOf(FXMLDatVeController.v)).setSoghe(txtGhe.getText());
            }
            else {
                Alert err = MessageBox.getBox("Thông báo!", "Chỗ đã được đặt trước", Alert.AlertType.ERROR);
                err.showAndWait();
            }
        }
        else if (txtChuyenXe.getText() != null && !txtChuyenXe.getText().isEmpty()){
            ChuyenXeServices cxs = new ChuyenXeServices();
            ChuyenXe cx1 = cxs.getCXTheoName(txtChuyenXe.getText());
            VeServices ves = new VeServices();
            Ve tmp = FXMLDatVeController.v;
            tmp.setChuyenxe_id(cx1.getId());
            if(ves.kiemTraVeDat(tmp, cx1)){
                FXMLDatVeController.listVeDaDat.get(FXMLDatVeController.listVeDaDat.indexOf(FXMLDatVeController.v)).setChuyenxe_id(cx1.getId());
            }
            else{
                Alert error = MessageBox.getBox("Lỗi!", "Đã quá hạn đổi vé", Alert.AlertType.ERROR);
            error.showAndWait();
            }
        }
        else{
            Alert error = MessageBox.getBox("Lỗi!", "Vui lòng nhập thông tin", Alert.AlertType.ERROR);
            error.showAndWait();
        }
    }
}
