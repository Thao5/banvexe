/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.KhoaBeoBenXeService;
import com.thao.Services.KhoaBeoChuyenXeService;
import com.thao.Services.KhoaBeoChuyenXeTuyenDuongSer;
import com.thao.Services.KhoaBeoUserService;
import com.thao.Services.KhoaBeoXeKhachService;
import com.thao.Utils.MessageBox;
import com.thao.pojo.BenXe;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.ChuyenXeThuocTuyenDuong;
import com.thao.pojo.TuyenDuong;
import com.thao.pojo.User;
import com.thao.pojo.XeKhach;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
public class FXMLUserController implements Initializable {

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
    private TableView<User> tbvuser;

    @FXML
    private TextField txtHo;
    @FXML
    private TextField txtTen;
    @FXML
    private TextField txtSDT;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox<Integer> cbBox1 = new ComboBox<>();
    @FXML
    private DatePicker dtPicker;
    private static KhoaBeoUserService users = new KhoaBeoUserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            tabPanel.getSelectionModel().select(5);
            this.LoadTableColums();
            this.loadTableData(null);
            ObservableList<Integer> items = FXCollections.observableArrayList(0, 1);
            this.cbBox1.setItems(items);
            this.cbBox1.setValue(0);
            this.addUserHandler();
            this.autoFillInfo();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //////////XỬ LÍ TABPANEL
        this.tabPanel.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov, Tab tab1, Tab tab2) -> {
            if (tabPanel.getSelectionModel().getSelectedIndex() == 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLAdmin.fxml"));
                Parent root1;
                try {
                    root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();

                    // Đóng scene hiện tại
                    Stage currentStage = (Stage) tabPanel.getScene().getWindow();
                    currentStage.close();

                } catch (IOException ex) {
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

    public void LoadTableColums() {

        this.tbvuser.getColumns().clear();

        TableColumn colHo = new TableColumn("Họ");
        colHo.setCellValueFactory(new PropertyValueFactory("ho"));

        TableColumn colName = new TableColumn("Ten");
        colName.setCellValueFactory(new PropertyValueFactory("ten"));

        TableColumn colSDT = new TableColumn("SDT");
        colSDT.setCellValueFactory(new PropertyValueFactory("sdt"));

        TableColumn colUsername = new TableColumn("UserName");
        colUsername.setCellValueFactory(new PropertyValueFactory("username"));

        TableColumn colPass = new TableColumn("Pass User");
        colPass.setCellValueFactory(new PropertyValueFactory("password"));
        TableColumn colAdmin = new TableColumn("Admin");
        colAdmin.setCellValueFactory(new PropertyValueFactory("admin"));

        TableColumn colBtn = new TableColumn();
        colBtn.setCellFactory(evt -> {

            Button btnXoa = new Button("Xóa");
            btnXoa.setOnAction(e -> {
                // Xuử lí Huy Ve
                Alert xacnhanXoaCx = MessageBox.getBox("Xóa User", "Bạn Có Chắc muốn xóa", Alert.AlertType.CONFIRMATION);
                xacnhanXoaCx.showAndWait().ifPresent(respon -> {
                    if (respon == ButtonType.OK) {
                        Button b = (Button) e.getSource();
                        TableCell cellv = (TableCell) b.getParent();
                        User v = (User) cellv.getTableRow().getItem();

                        try {
                            if (users.deleteUser(v.getId())) {
                                MessageBox.getBox("Xóa User", "Thành Công", Alert.AlertType.INFORMATION).show();
                                this.loadTableData(null);
                            } else {
                                MessageBox.getBox("Xóa User", "Faild", Alert.AlertType.INFORMATION).show();
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
                Alert xacNhanDoiVe = MessageBox.getBox("Cập nhật !!!!", "BẠN CÓ CHẮC MUỐN Cập nhật chuyến NÀY ", Alert.AlertType.CONFIRMATION);
                xacNhanDoiVe.showAndWait().ifPresent(respon -> {
                    if (respon == ButtonType.OK) {
                        Button b = (Button) e.getSource();
                        TableCell cellv = (TableCell) b.getParent();
                        User v = (User) cellv.getTableRow().getItem();
                        boolean value = this.cbBox1.getValue() == 1 ? true : false;
                        if (checkSoDienThoai(this.txtSDT.getText()) && checkInfoEmty(this.txtHo.getText(), this.txtTen.getText(), this.txtUsername.getText(), this.txtPassword.getText())) {
                            User us = new User(v.getId(), this.txtHo.getText(), this.txtTen.getText(), this.txtSDT.getText(), this.txtUsername.getText(), this.txtPassword.getText(), value);
                            try {
                                if (users.updateUser(us)) {
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
                        }else {
                        Alert confirm = MessageBox.getBox("Lỗi nhập liệu", "Vui Lòng nhập lại", AlertType.INFORMATION);
                        confirm.showAndWait();
                        }

                    }
                });
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btnDoiVe);
            return cell;
        });
        this.tbvuser.getColumns().addAll(colHo, colName, colSDT, colUsername, colPass, colAdmin,colBtn,colBtn2);
    }

    private void loadTableData(String username) throws SQLException {

        List<User> listUser = users.getListUser(username);
        this.tbvuser.getItems().clear();
        this.tbvuser.setItems(FXCollections.observableList(listUser));
    }

    public void addUserHandler() throws SQLException {

        btnThem.setOnAction(e -> {

            // Xuử lí Huy Ve
            Alert xacnhanXoaCx = MessageBox.getBox("Thêm User", "Bạn Có Chắc muốn Them Những thông tin hiện tại", Alert.AlertType.CONFIRMATION);
            xacnhanXoaCx.showAndWait().ifPresent(respon -> {
                if (respon == ButtonType.OK) {
                    boolean value = this.cbBox1.getValue() == 1 ? true : false;
                    if (checkSoDienThoai(this.txtSDT.getText()) && checkInfoEmty(this.txtHo.getText(), this.txtTen.getText(), this.txtUsername.getText(), this.txtPassword.getText())) {
                        User us = new User(this.txtHo.getText(), this.txtTen.getText(), this.txtSDT.getText(), this.txtUsername.getText(), this.txtPassword.getText(), value);
                        try {
                            if (users.insertUser(us)) {
                                MessageBox.getBox("Trạng Thái", "Thành Công", Alert.AlertType.INFORMATION).show();
                                this.loadTableData(null);
                            } else {
                                MessageBox.getBox("Trạng Thái", "Faild", Alert.AlertType.INFORMATION).show();
                                this.loadTableData(null);
                            }

                        } catch (SQLException ex) {
                            MessageBox.getBox("Trạng Thái", "Faild", Alert.AlertType.INFORMATION).show();
                            Logger.getLogger(FXMLTimVeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        Alert confirm = MessageBox.getBox("Lỗi nhập liệu", "Vui Lòng nhập lại", AlertType.INFORMATION);
                        confirm.showAndWait();
                        this.txtSDT.clear();

                    }

                }
            });
        });
    }

    public boolean checkSoDienThoai(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public boolean checkInfoEmty(String ho, String ten, String userName, String pass) {
        boolean result = false;
        if (ho.isEmpty() || ten.isEmpty() || userName.isEmpty() || pass.isEmpty()) {
            return result;
        }
        result = true;
        return result;
    }
    public void autoFillInfo() {
        this.tbvuser.setOnMouseClicked(e -> {
            if (e.getClickCount() == 1) {
                User selectedUser = tbvuser.getSelectionModel().getSelectedItem();

                // Điền thông tin vào các TextField tương ứng
                txtHo.setText(selectedUser.getHo());
                txtTen.setText(selectedUser.getTen());
                txtSDT.setText(selectedUser.getSdt());
                txtUsername.setText(selectedUser.getUsername());
                txtPassword.setText(selectedUser.getPassword());
                cbBox1.setValue(selectedUser.isAdmin() ? 1 : 0);
            }
        });

    }

}
