/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.KhoaBeoGheService;
import com.thao.pojo.Ghe;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author anhkh
 */
public class FXMLGheController implements Initializable {

    @FXML
    private TableView<Ghe> tbGhe;

    private void loadTableData(String kw) throws SQLException {
        KhoaBeoGheService lsg = new KhoaBeoGheService();
        List<Ghe> listGhe = lsg.getListGhe(kw);
        this.tbGhe.setItems(FXCollections.observableList(listGhe));
    }

    private void loadTableColumn() {

        TableColumn colMa = new TableColumn("Mã");
        colMa.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn colCho = new TableColumn("Số Ghế");
        colCho.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn colTrangThai = new TableColumn("Trang Thai");
        colTrangThai.setCellValueFactory(new PropertyValueFactory("trangthai"));
        colTrangThai.setCellFactory(column -> {
            return new TableCell<Ghe, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText("");
                    } else {
                        if (item) {
                            setText("Đã đặt");
                        } else {
                            setText("Còn trống");
                        }
                    }
                }
            };
        });
        TableColumn colVeid = new TableColumn("ve_id");
        colVeid.setCellValueFactory(new PropertyValueFactory("ve_id"));

        TableColumn colXK = new TableColumn("XeKhach");
        colXK.setCellValueFactory(new PropertyValueFactory("xekhach_id"));

        this.tbGhe.getColumns().addAll(colCho, colTrangThai,colXK);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTableColumn();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGheController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
