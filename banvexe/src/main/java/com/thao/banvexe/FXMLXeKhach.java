/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.KhoaBeoXeKhachService;
import com.thao.Utils.MessageBox;
import com.thao.pojo.BenXe;
import com.thao.pojo.XeKhach;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class FXMLXeKhach implements Initializable {
    @FXML
    private TabPane tabPanel;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private TableView<XeKhach> tbvxk;
    @FXML
    private TextField txtSoChoNgoi;
    @FXML
    private TextField txtBienSo;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnThem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            tabPanel.getSelectionModel().select(6);

            this.LoadTableColums();
            this.loadTableData(null);
            this.addXeKhachhandler();
            this.txtSearch.textProperty().addListener(d -> {
                try {
                    
                    this.loadTableData(this.txtSearch.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

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
            }else if (tabPanel.getSelectionModel().getSelectedIndex() == 3) {
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
            }else if (tabPanel.getSelectionModel().getSelectedIndex() == 4) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLTuyenDuong.fxml"));
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
            }else if (tabPanel.getSelectionModel().getSelectedIndex() == 5) {
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
            }else if (tabPanel.getSelectionModel().getSelectedIndex() == 6) {
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
        this.tbvxk.getColumns().clear();
        TableColumn colName = new TableColumn("Số chổ ngồi");
        colName.setCellValueFactory(new PropertyValueFactory("sochongoi"));

        TableColumn coladress = new TableColumn("Biển số xe");
        coladress.setCellValueFactory(new PropertyValueFactory("bienso"));
        /////// add nút
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
                        XeKhach v = (XeKhach) cellv.getTableRow().getItem();
                        KhoaBeoXeKhachService xks = new KhoaBeoXeKhachService();
                        try {
                            if (xks.deleteXeKhach(v.getId())) {
                                MessageBox.getBox("Xóa Xe Khach", "Thành Công", Alert.AlertType.INFORMATION).show();
                                this.loadTableData(null);
                            } else {
                                MessageBox.getBox("Xóa Xoa Xe Khach", "Faild", Alert.AlertType.INFORMATION).show();
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
                Alert xacNhanDoiVe = MessageBox.getBox("Cập nhật !!!!", "BẠN CÓ CHẮC MUỐN Cập nhật Xe Khach NÀY ", Alert.AlertType.CONFIRMATION);
                xacNhanDoiVe.showAndWait().ifPresent(respon -> {
                    if (respon == ButtonType.OK) {
                      

                        Button b = (Button) e.getSource();
                        TableCell cellv = (TableCell) b.getParent();
                        XeKhach v = (XeKhach) cellv.getTableRow().getItem();
                        KhoaBeoXeKhachService xks = new KhoaBeoXeKhachService();
                        XeKhach addbx = new XeKhach(
                                v.getId(), // set the ID of the selected record
                               Integer.parseInt(this.txtSoChoNgoi.getText()),
                                this.txtBienSo.getText()
                        );
                        try {
                            if (xks.updateXeKhach(addbx)) {
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
        this.tbvxk.getColumns().addAll(colName, coladress,colBtn,colBtn2); // sửa đổi tên của TableView ở đây
    }

    private void loadTableData(String kw) throws SQLException {
        KhoaBeoXeKhachService xks = new KhoaBeoXeKhachService();
        List<XeKhach> listxk = xks.getXeKhach(kw);
        this.tbvxk.getItems().clear();
        this.tbvxk.setItems(FXCollections.observableList(listxk));
    }
 public void addXeKhachhandler() throws SQLException {

        btnThem.setOnAction(e -> {
            KhoaBeoXeKhachService xks = new KhoaBeoXeKhachService();
            // Xuử lí Huy Ve
            Alert xacnhanXoaCx = MessageBox.getBox("Thêm Xe Khach", "Bạn Có Chắc muốn Them Những thông tin hiện tại", Alert.AlertType.CONFIRMATION);
            xacnhanXoaCx.showAndWait().ifPresent(respon -> {
                if (respon == ButtonType.OK) {
                   
                        XeKhach addbx = new XeKhach(Integer.parseInt(this.txtSoChoNgoi.getText()), this.txtBienSo.getText());
                    try {
                        if (xks.insertXeKhach(addbx)) {
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
                }
            });
        });
    }
}
