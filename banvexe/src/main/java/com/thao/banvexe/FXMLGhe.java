/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.KhoaBeoChuyenXeService;
import com.thao.Services.KhoaBeoGheService;
import com.thao.Services.KhoaBeoUserService;
import com.thao.Services.KhoaBeoXeKhachService;
import com.thao.Utils.MessageBox;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.Ghe;
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
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class FXMLGhe implements Initializable {

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
    private TableView<Ghe> tbvghe;

    @FXML
    private TextField txtTenGhe;

    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox<XeKhach> cbBox1;
    @FXML

    private static KhoaBeoGheService gs = new KhoaBeoGheService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        KhoaBeoXeKhachService xk = new KhoaBeoXeKhachService();
        try {
            List<XeKhach> lxk = xk.getXeKhach();
            tabPanel.getSelectionModel().select(3);
            this.LoadTableColums();
            this.loadTableData(null);
            this.cbBox1.setItems(FXCollections.observableList(lxk));
            this.cbBox1.setValue(lxk.get(0));
            this.addGheHandler();
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

        this.tbvghe.getColumns().clear();

        TableColumn colName = new TableColumn("Tên Ghe");
        colName.setCellValueFactory(new PropertyValueFactory("name"));

        TableColumn colTrangThai = new TableColumn("trangthai");
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
        TableColumn colCx = new TableColumn("Xe Khách");
        colCx.setCellValueFactory(new PropertyValueFactory("xekhach_id"));
         TableColumn colBtn = new TableColumn();
        colBtn.setCellFactory(evt -> {

            Button btnXoa = new Button("Xóa");
            btnXoa.setOnAction(e -> {
                // Xuử lí Huy Ve
                Alert xacnhanXoaCx = MessageBox.getBox("Xóa Ghế", "Bạn Có Chắc muốn xóa", Alert.AlertType.CONFIRMATION);
                xacnhanXoaCx.showAndWait().ifPresent(respon -> {
                    if (respon == ButtonType.OK) {
                        Button b = (Button) e.getSource();
                        TableCell cellv = (TableCell) b.getParent();
                        Ghe v = (Ghe) cellv.getTableRow().getItem();
             
                        try {
                            if (gs.deleteGhe(v.getId())) {
                                MessageBox.getBox("Xóa Ghế", "Thành Công", Alert.AlertType.INFORMATION).show();
                                this.loadTableData(null);
                            } else {
                                MessageBox.getBox("Xóa Ghế", "Faild", Alert.AlertType.INFORMATION).show();
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
                Alert xacNhanDoiVe = MessageBox.getBox("Cập nhật !!!!", "BẠN CÓ CHẮC MUỐN Cập nhật ", Alert.AlertType.CONFIRMATION);
                xacNhanDoiVe.showAndWait().ifPresent(respon -> {
                    if (respon == ButtonType.OK) {
                       
                        Button b = (Button) e.getSource();
                        TableCell cellv = (TableCell) b.getParent();
                        Ghe v = (Ghe) cellv.getTableRow().getItem();
                        Ghe addcx = new Ghe(
                                v.getId(), // set the ID of the selected record
                              this.txtTenGhe.getText(),
                                false,"",this.cbBox1.getSelectionModel().getSelectedItem().getId()

                        );
                        try {
                            if (gs.updateGheEmpty2(addcx)) {
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
                });
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btnDoiVe);
            return cell;
        });

        this.tbvghe.getColumns().addAll(colName, colTrangThai, colCx,colBtn,colBtn2);

    }

    private void loadTableData(String kw) throws SQLException {

        List<Ghe> listGhe = gs.getListGhe(kw);
        this.tbvghe.getItems().clear();
        this.tbvghe.setItems(FXCollections.observableList(listGhe));
    }
     public void addGheHandler() throws SQLException {

        btnThem.setOnAction(e -> {

            // Xuử lí Huy Ve
            Alert xacnhanXoaCx = MessageBox.getBox("Thêm Ghế", "Bạn Có Chắc muốn Them Những thông tin hiện tại", Alert.AlertType.CONFIRMATION);
            xacnhanXoaCx.showAndWait().ifPresent(respon -> {
                if (respon == ButtonType.OK) {
                    if(checkInfoEmty(this.txtTenGhe.getText())) {
                        boolean value = false;
                        
                        Ghe g = new Ghe(this.txtTenGhe.getText(), value,"",this.cbBox1.getSelectionModel().getSelectedItem().getId());
                        try {
                            if (gs.insertGhe(g)) {
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
                        Alert confirm = MessageBox.getBox("Lỗi nhập liệu", "Vui Lòng nhập lại", Alert.AlertType.INFORMATION);
                        confirm.showAndWait();
                        

                    }

                }
            });
        });
    }
     public boolean checkInfoEmty(String ten) {
        boolean result = false;
        if ( ten.isEmpty()) {
            return result;
        }
        result = true;
        return result;
    }
     public void autoFillInfo() {
        this.tbvghe.setOnMouseClicked(e -> {
            if (e.getClickCount() == 1) {
                Ghe selectedGhe = tbvghe.getSelectionModel().getSelectedItem();
                 txtTenGhe.setText(selectedGhe.getName());

            }
        });

}
}
