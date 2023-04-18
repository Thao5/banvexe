/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.KhoaBeoBenXeService;
import com.thao.Services.KhoaBeoChuyenXeService;
import com.thao.Services.KhoaBeoVeXeService;
import com.thao.Services.KhoaBeoXeKhachService;
import com.thao.Utils.MessageBox;
import com.thao.pojo.BenXe;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.Ve;
import com.thao.pojo.XeKhach;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author anhkh
 */
public class FXMLAdminController implements Initializable {

    @FXML
    private TabPane tabPanel;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private Button btnCapNhat;
    @FXML
    private Button btnThem;
    @FXML
    private TableView<ChuyenXe> tbvcx;
    @FXML
    private TableView<ChuyenXe> tbvbx;
    @FXML
    private ComboBox<BenXe> bx1;
    @FXML
    private ComboBox<BenXe> bx2;
    @FXML
    private ComboBox<XeKhach> cbXk;
    @FXML
    private ComboBox<Integer> comboHours;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSoGhe;
    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtPrice;
    @FXML
    private DatePicker dtPicker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KhoaBeoBenXeService bx = new KhoaBeoBenXeService();
        KhoaBeoXeKhachService xk = new KhoaBeoXeKhachService();
        try {

            List<Integer> hours = IntStream.range(0, 24).boxed().collect(Collectors.toList());
            comboHours.getItems().addAll(hours);
            comboHours.setValue(LocalTime.now().getHour());
            dtPicker.setValue(LocalDate.now());
            List<BenXe> lbx = bx.getBenXe();
            List<XeKhach> lxk = xk.getXeKhach();
            this.bx1.setItems(FXCollections.observableList(lbx));
            this.bx2.setItems(FXCollections.observableList(lbx));
            this.cbXk.setItems(FXCollections.observableList(lxk));
            this.bx1.getSelectionModel().selectFirst();
            this.cbXk.getSelectionModel().selectFirst();

            this.LoadTableColums();
            this.loadTableData(null);
            this.autoFillInfo();
            this.txtSearch.textProperty().addListener(d -> {
                try {
                    this.loadTableData(this.txtSearch.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            this.addChuyenXehandler();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //////////XỬ LÍ TABPANEL
        this.tabPanel.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov, Tab tab1, Tab tab2) -> {
            if (tabPanel.getSelectionModel().getSelectedIndex() == 0) {
                try {
                    this.LoadTableColums();
                    this.loadTableData(null);
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tabPanel.getSelectionModel().getSelectedIndex() == 1) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLBenXe.fxml"));
                Parent root1;
                try {
                    root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    tabPanel.getSelectionModel().select(1);
                    // Đóng scene hiện tại
                    Stage currentStage = (Stage) tabPanel.getScene().getWindow();
                    currentStage.close();

                } catch (IOException ex) {
                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (tabPanel.getSelectionModel().getSelectedIndex() == 2) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLCX_TD.fxml"));
                Parent root1;
                try {
                    root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    tabPanel.getSelectionModel().select(2);
                    // Đóng scene hiện tại
                    Stage currentStage = (Stage) tabPanel.getScene().getWindow();
                    currentStage.close();

                } catch (IOException ex) {
                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tabPanel.getSelectionModel().getSelectedIndex() == 3) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLGhe.fxml"));
                Parent root1;
                try {
                    root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    tabPanel.getSelectionModel().select(3);
                    // Đóng scene hiện tại
                    Stage currentStage = (Stage) tabPanel.getScene().getWindow();
                    currentStage.close();

                } catch (IOException ex) {
                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tabPanel.getSelectionModel().getSelectedIndex() == 3) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLGhe.fxml"));
                Parent root1;
                try {
                    root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    tabPanel.getSelectionModel().select(3);
                    // Đóng scene hiện tại
                    Stage currentStage = (Stage) tabPanel.getScene().getWindow();
                    currentStage.close();

                } catch (IOException ex) {
                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tabPanel.getSelectionModel().getSelectedIndex() == 4) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLTuyenduong.fxml"));
                Parent root1;
                try {
                    root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    tabPanel.getSelectionModel().select(4);
                    // Đóng scene hiện tại
                    Stage currentStage = (Stage) tabPanel.getScene().getWindow();
                    currentStage.close();

                } catch (IOException ex) {
                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tabPanel.getSelectionModel().getSelectedIndex() == 5) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLUser.fxml"));
                Parent root1;
                try {
                    root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    tabPanel.getSelectionModel().select(5);
                    // Đóng scene hiện tại
                    Stage currentStage = (Stage) tabPanel.getScene().getWindow();
                    currentStage.close();

                } catch (IOException ex) {
                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tabPanel.getSelectionModel().getSelectedIndex() == 6) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLXeKhach.fxml"));
                Parent root1;
                try {
                    root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();
                    tabPanel.getSelectionModel().select(6);
                    // Đóng scene hiện tại
                    Stage currentStage = (Stage) tabPanel.getScene().getWindow();
                    currentStage.close();

                } catch (IOException ex) {
                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    public void LoadTableColumsBX() {
        this.tbvcx.getColumns().clear();
        TableColumn colName = new TableColumn("Tên Bến Xe");
        colName.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn coladress = new TableColumn("Địa Điểm");
        coladress.setCellValueFactory(new PropertyValueFactory("address"));

        this.tbvbx.getColumns().addAll(colName, coladress); // sửa đổi tên của TableView ở đây
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
        TableColumn colBtn = new TableColumn();
        colBtn.setCellFactory(evt -> {

            Button btnXoa = new Button("Xóa");
            btnXoa.setOnAction(e -> {
                // Xuử lí Huy Ve
                Alert xacnhanXoaCx = MessageBox.getBox("Xóa Chuyến", "Bạn Có Chắc muốn xóa", Alert.AlertType.CONFIRMATION);
                xacnhanXoaCx.showAndWait().ifPresent(respon -> {
                    if (respon == ButtonType.OK) {
                        Button b = (Button) e.getSource();
                        TableCell cellv = (TableCell) b.getParent();
                        ChuyenXe v = (ChuyenXe) cellv.getTableRow().getItem();
                        KhoaBeoChuyenXeService cx = new KhoaBeoChuyenXeService();
                        try {
                            if (cx.deleteChuyenXe(v.getId())) {
                                MessageBox.getBox("Xóa Chuyến", "Thành Công", Alert.AlertType.INFORMATION).show();
                                this.loadTableData(null);
                            } else {
                                MessageBox.getBox("Xóa Chuyến", "Faild", Alert.AlertType.INFORMATION).show();
                            }
                        } catch (SQLException ex) {

                            Logger.getLogger(FXMLTimVeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btnXoa);
            return cell;
        });

        TableColumn colBtn2 = new TableColumn();
        colBtn2.setCellFactory(evt -> {
            Button btnDoiVe = new Button("Cập nhật!!!!");
            btnDoiVe.setOnAction(e -> {
                if (checkInfoText(this.txtName.getText(), this.txtPrice.getText())) {
                    Alert xacNhanDoiVe = MessageBox.getBox("Cập nhật !!!!", "BẠN CÓ CHẮC MUỐN Cập nhật chuyến NÀY ", Alert.AlertType.CONFIRMATION);
                    xacNhanDoiVe.showAndWait().ifPresent(respon -> {
                        if (respon == ButtonType.OK) {
                            if (checkInfoText(this.txtName.getText(), this.txtPrice.getText())) {

                                LocalDate date = this.dtPicker.getValue();
                                int selectedHour = comboHours.getValue();
                                LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(selectedHour, 0));

                                Button b = (Button) e.getSource();
                                TableCell cellv = (TableCell) b.getParent();
                                ChuyenXe v = (ChuyenXe) cellv.getTableRow().getItem();
                                KhoaBeoChuyenXeService cx = new KhoaBeoChuyenXeService();
                                ChuyenXe addcx = new ChuyenXe(
                                        v.getId(), // set the ID of the selected record
                                        this.txtName.getText(),
                                        dateTime,
                                        Double.valueOf(this.txtPrice.getText()),
                                        this.cbXk.getSelectionModel().getSelectedItem().getId(),
                                        this.bx1.getSelectionModel().getSelectedItem().getId(),
                                        this.bx2.getSelectionModel().getSelectedItem().getId()
                                );
                                try {
                                    if (cx.updateCx(addcx)) {
                                        MessageBox.getBox("Trạng Thái", "Thành Công", Alert.AlertType.INFORMATION).show();
                                        this.loadTableData(null);
                                    } else {
                                        MessageBox.getBox("Trạng Thái", "Faild", Alert.AlertType.INFORMATION).show();
                                        this.loadTableData(null);
                                    }
                                } catch (SQLException ex) {

                                    Logger.getLogger(FXMLAdminController.class.getName()).log(Level.SEVERE, null, ex);
                                    MessageBox.getBox("Trạng Thái", "Faild", Alert.AlertType.INFORMATION).show();
                                }

                            }

                        }
                    });
                }else {
                Alert confirm = MessageBox.getBox("Lỗi", "Nhập thông tin không hợp lệ", Alert.AlertType.ERROR);
                this.txtPrice.clear();
                confirm.showAndWait();
            }
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btnDoiVe);
            return cell;
        });

        this.tbvcx.getColumns().addAll(colName, colNgayKhoiHanh, colGiaVe, colXeKhach, colBenXeDi, colBenXeDen, colBtn, colBtn2);
    }

    private void loadTableData(String kw) throws SQLException {
        KhoaBeoChuyenXeService cxs = new KhoaBeoChuyenXeService();
        List<ChuyenXe> listCX = cxs.getChuyenXe(kw);
        this.tbvcx.getItems().clear();
        this.tbvcx.setItems(FXCollections.observableList(listCX));
    }

    public void addChuyenXehandler() throws SQLException {

        btnThem.setOnAction(e -> {
            if (checkInfoText(this.txtName.getText(), this.txtPrice.getText())) {

                KhoaBeoChuyenXeService cxv = new KhoaBeoChuyenXeService();
                // Xuử lí Huy Ve
                Alert xacnhanXoaCx = MessageBox.getBox("Them Chuyến", "Bạn Có Chắc muốn Them Những thông tin hiện tại", Alert.AlertType.CONFIRMATION);
                xacnhanXoaCx.showAndWait().ifPresent(respon -> {

                    if (respon == ButtonType.OK) {

                        LocalDate date = this.dtPicker.getValue();

                        int selectedHour = comboHours.getValue();
                        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.of(selectedHour, 0));
                        ChuyenXe addcx = new ChuyenXe(
                                this.txtName.getText(),
                                dateTime,
                                Double.valueOf(this.txtPrice.getText()),
                                this.cbXk.getSelectionModel().getSelectedItem().getId(),
                                this.bx1.getSelectionModel().getSelectedItem().getId(),
                                this.bx2.getSelectionModel().getSelectedItem().getId()
                        );
                        try {
                            if (cxv.insertCx(addcx)) {
                                MessageBox.getBox("Trạng Thái", "Thành Công", Alert.AlertType.INFORMATION).show();
                                this.loadTableData(null);
                            } else {
                                MessageBox.getBox("Trạng Thái", "Faild", Alert.AlertType.INFORMATION).show();
                                this.loadTableData(null);
                            }

                        } catch (SQLException ex) {
                            MessageBox.getBox("Trạng Thái", "Faild", Alert.AlertType.INFORMATION).show();

                        }
                    }

                });
            } else {
                Alert confirm = MessageBox.getBox("Lỗi", "Nhập thông tin không hợp lệ", Alert.AlertType.ERROR);
                this.txtPrice.clear();
                confirm.showAndWait();
            }

        });
    }

    public void checkNgayHopLe(ActionEvent e) {
        LocalDate selectedDate = dtPicker.getValue();
        LocalDate currentDate = LocalDate.now();
        if (selectedDate.isBefore(currentDate)) {
            Alert confirm = MessageBox.getBox("Lỗi", "Ngày không hợp lệ, Vui lòng chọn một ngày trong tương lai", Alert.AlertType.ERROR);

            confirm.showAndWait();
            dtPicker.setValue(LocalDate.now());
        }
    }

    public void CheckDiemDiDen(ActionEvent e) {
        Object selectCombo1 = bx1.getValue();
        Object selectCombo2 = bx2.getValue();
        if (selectCombo1 != null && selectCombo1.equals(selectCombo2)) {

            bx1.getSelectionModel().clearSelection();
            bx2.getSelectionModel().clearSelection();
            Alert confirm = MessageBox.getBox("Lỗi", "Chọn Điểm Đi Và Đến bị trùng", Alert.AlertType.ERROR);

            confirm.showAndWait();
        }

    }

    public boolean checkInfoText(String tenChuyenXe, String sotien) {
        Boolean result = false;
        if (!tenChuyenXe.isEmpty() && !sotien.isEmpty() && Double.valueOf(this.txtPrice.getText()) > 0) {
            result = true;
            return result;
        }
        return result;
    }

    public void autoFillInfo() {
        this.tbvcx.setOnMouseClicked(e -> {
            if (e.getClickCount() == 1) {
                ChuyenXe selectedCX = tbvcx.getSelectionModel().getSelectedItem();

                txtName.setText(String.valueOf(selectedCX.getName()));
                txtPrice.setText(selectedCX.getGiave().toString());
            }
        });

    }
}
