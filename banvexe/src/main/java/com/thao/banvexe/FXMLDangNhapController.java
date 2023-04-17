/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.UserServices;
import com.thao.Utils.MessageBox;
import com.thao.pojo.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Chung Vu
 */
public class FXMLDangNhapController {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnDangNhap;
    
    public static User account;
    
    
    public void dangNhap(ActionEvent evt){
        UserServices urs = new UserServices();
        if(urs.getUser(txtUsername.getText(), txtPassword.getText()) != null){
            account = urs.getUser(txtUsername.getText(), txtPassword.getText());
            Stage stage = (Stage) btnDangNhap.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDatVe.fxml"));
                        Parent root1;
                        try {
                            root1 = (Parent) fxmlLoader.load();
                            stage = new Stage();
                            stage.setScene(new Scene(root1));
                            stage.show(); 
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        }
        else{
            Alert error = MessageBox.getBox("Lỗi", "Sai mật khẩu hoặc tài khoãn", Alert.AlertType.ERROR);
            error.showAndWait();
        }
    }
}
