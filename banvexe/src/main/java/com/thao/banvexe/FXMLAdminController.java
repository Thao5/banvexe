/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.KhoaBeoBenXeService;
import com.thao.Services.KhoaBeoChuyenXeService;
import com.thao.Services.KhoaBeoXeKhachService;
import com.thao.pojo.BenXe;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.XeKhach;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author anhkh
 */
public class FXMLAdminController implements Initializable {

    @FXML
    private TableView<ChuyenXe> tbvcx;
    @FXML
    private ComboBox<BenXe> bx1;
    @FXML
    private ComboBox<BenXe> bx2;
    @FXML
    private ComboBox<XeKhach> cbXk;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSoGhe;
    @FXML
    private TextField txtPrice;
    @FXML
    private DatePicker dtPicker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KhoaBeoBenXeService bx = new KhoaBeoBenXeService();
        KhoaBeoXeKhachService xk = new KhoaBeoXeKhachService();
        try {
            List<BenXe> lbx = bx.getBenXe();
            List<XeKhach> lxk = xk.getXeKhach();
            this.bx1.setItems(FXCollections.observableList(lbx));
            this.bx2.setItems(FXCollections.observableList(lbx));
            this.cbXk.setItems(FXCollections.observableList(lxk));
            this.LoadTableColums();
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadTableColums() {
        this.tbvcx.getColumns().clear();
        TableColumn colName = new TableColumn("Tên chuyến đi");
        colName.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn colNgayKhoiHanh = new TableColumn("Ngày Khởi Hành");
        colNgayKhoiHanh.setCellValueFactory(new PropertyValueFactory("ngaykhoihanh"));

        TableColumn colGiaVe = new TableColumn("Giá Vé");
        colGiaVe.setCellValueFactory(new PropertyValueFactory("giave"));

        TableColumn colXeKhach = new TableColumn("Xe Khách");
        colXeKhach.setCellValueFactory(new PropertyValueFactory("xekhach_id"));

        TableColumn colBenXeDi = new TableColumn("Bến Xe Đi");
        colBenXeDi.setCellValueFactory(new PropertyValueFactory("benxedi_id"));
//        colBenXeDi.setCellValueFactory(cellData -> {
//            
//        });
        TableColumn colBenXeDen = new TableColumn("Bến Xe Đến");
        colBenXeDen.setCellValueFactory(new PropertyValueFactory("benxeden_id"));
        this.tbvcx.getColumns().addAll(colName, colNgayKhoiHanh, colGiaVe, colXeKhach, colBenXeDi, colBenXeDen);
    }

    private void loadTableData(String kw) throws SQLException {
        KhoaBeoChuyenXeService cxs = new KhoaBeoChuyenXeService();
        List<ChuyenXe> listCX = cxs.getChuyenXe(kw);
        this.tbvcx.getItems().clear();
        this.tbvcx.setItems(FXCollections.observableList(listCX));
    }

    public void addChuyenXehandler(ActionEvent e) throws SQLException {
        LocalDate date = this.dtPicker.getValue();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = date.atTime(time);
        ChuyenXe addcx = new ChuyenXe(
                this.txtName.getText(), // lấy tên từ textfield
                dateTime, // lấy ngày khởi hành từ datetime picker
                Double.parseDouble(this.txtPrice.getText()), // lấy giá vé từ textfield và chuyển về kiểu Double
                this.cbXk.getSelectionModel().getSelectedItem().getId(), // lấy ID xe khách được chọn từ combobox
                this.bx1.getSelectionModel().getSelectedItem().getId(), // lấy ID bến xe đi được chọn từ combobox
                this.bx2.getSelectionModel().getSelectedItem().getId() // lấy ID bến xe đến được chọn từ combobox
        );

        KhoaBeoChuyenXeService cxv = new KhoaBeoChuyenXeService();
        cxv.insertCx(addcx);
    }

}
