/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.KhoaBeoChuyenXeService;
import com.thao.Services.KhoaBeoGheService;
import com.thao.Services.KhoaBeoVeXeService;
import com.thao.Utils.MessageBox;
import com.thao.pojo.Ve;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author anhkh
 */
public class FXMLDoiVe implements Initializable {

    private Ve selectedVe;

    public void setModel(Ve v) throws SQLException {
        selectedVe = v;
        txt1.setText(v.getSoghe());
        txt2.setText(v.getChuyenxe_id());

        // Lấy lại thông tin với id của selectedVe
        KhoaBeoVeXeService s = new KhoaBeoVeXeService();
        Ve updatedVe = s.getVeById(selectedVe.getId()); // Hàm getVeById trả về đối tượng Ve với id truyền vào
        selectedVe.setSoghe(updatedVe.getSoghe());
        selectedVe.setChuyenxe_id(updatedVe.getChuyenxe_id());
    }

    @FXML
    private Button btn;
    @FXML
    private Button updateV;
    @FXML
    private TextField txt1;
    @FXML
    private TextField txt2;

    private void getDanhSachGhe() {
        btn.setOnAction(e -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLXemGhe.fxml"));
            Parent root1;
            try {
                root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void handleCapNhatVe() throws SQLException {
        updateV.setOnAction(e -> {

            KhoaBeoVeXeService s = new KhoaBeoVeXeService();
            KhoaBeoGheService g = new KhoaBeoGheService();
            KhoaBeoChuyenXeService cx = new KhoaBeoChuyenXeService();
            // Lấy thông tin vé từ các thành phần giao diện
            String gheCu = selectedVe.getSoghe();
            String cxOld = selectedVe.getChuyenxe_id();
            Boolean checkTimeOld = null;
            try {
                checkTimeOld = cx.checkTimeCX(cxOld);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDoiVe.class.getName()).log(Level.SEVERE, null, ex);
            }
            String soGhe = txt1.getText();
            String cxid = txt2.getText();

            //Cập nhật thông tin vé
            selectedVe.setSoghe(soGhe);
            selectedVe.setChuyenxe_id(cxid);
            if(checkTimeOld){
             try {
                boolean result = s.updateVe(selectedVe.getSoghe(), selectedVe.getChuyenxe_id(), selectedVe.getId());
                
                if (result) {
                    if (g.updateGhe(selectedVe.getId(), soGhe)) {
                        g.updateGheEmpty(selectedVe.getId(), gheCu);
                        Alert confirm = MessageBox.getBox("Trạng thái", "Đổi vé thành công!!!", Alert.AlertType.INFORMATION);

                        confirm.showAndWait().ifPresent(respon -> {

                            if (respon == ButtonType.OK) {
                                Stage stage = (Stage) updateV.getScene().getWindow();
                                stage.close();
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLTimVe.fxml"));
                                Parent root1;
                                try {
                                    root1 = (Parent) fxmlLoader.load();
                                    stage.setScene(new Scene(root1));

                                    stage.show();
                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    } else  if (g.updateGhe(selectedVe.getId(), soGhe)==false){
                        MessageBox.getBox("Trạng thái", "Đổi vé Faild!!!", Alert.AlertType.INFORMATION).show();
                    }
                }
                else {
                    
                     Alert confirm = MessageBox.getBox("Trạng thái", "Đổi vé FAILDED!!!", Alert.AlertType.INFORMATION);

                        confirm.showAndWait().ifPresent(respon -> {

                            if (respon == ButtonType.OK) {
                                Stage stage = (Stage) updateV.getScene().getWindow();
                                stage.close();
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLTimVe.fxml"));
                                Parent root1;
                                try {
                                    root1 = (Parent) fxmlLoader.load();
                                    stage.setScene(new Scene(root1));

                                    stage.show();
                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDoiVe.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
             else{
                Alert confirm = MessageBox.getBox("Trạng thái", "Quá thời gian đổi vé!!!", Alert.AlertType.INFORMATION);

                        confirm.showAndWait().ifPresent(respon -> {

                            if (respon == ButtonType.OK) {
                                Stage stage = (Stage) updateV.getScene().getWindow();
                                stage.close();
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLTimVe.fxml"));
                                Parent root1;
                                try {
                                    root1 = (Parent) fxmlLoader.load();
                                    stage.setScene(new Scene(root1));

                                    stage.show();
                                } catch (IOException ex) {
                                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
            }
            System.out.println(selectedVe);

//        if (updateV != null) {
//            
//        }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.getDanhSachGhe();
        try {
            this.handleCapNhatVe();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDoiVe.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
