/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.KhoaBeoTuyenduongService;
import com.thao.Services.KhoaBeoXeKhachService;
import com.thao.Utils.MessageBox;
import com.thao.pojo.TuyenDuong;
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
public class FXMLTuyenDuongController implements Initializable {

    @FXML
    private TabPane tabPanel;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private TableView<TuyenDuong> tbvtd;
    @FXML
    private TextField txtDiemDi;
    @FXML
    private TextField txtDiemDen;
    @FXML
    private TextField txtSearch;
    @FXML
    private Button btnThem;
    private static KhoaBeoTuyenduongService tds = new KhoaBeoTuyenduongService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            tabPanel.getSelectionModel().select(4);

            this.LoadTableColums();
            this.loadTableData(null);
            this.addTuyenDuongHandler();
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
            } else if (tabPanel.getSelectionModel().getSelectedIndex() ==2 ) {
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLXemGhe.fxml"));
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
            }else if (tabPanel.getSelectionModel().getSelectedIndex() == 11) {
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
            }   else if (tabPanel.getSelectionModel().getSelectedIndex() == 5) {
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
        this.tbvtd.getColumns().clear();
        TableColumn colName = new TableColumn("Điểm Đi");
        colName.setCellValueFactory(new PropertyValueFactory("diemdi"));

        TableColumn coladress = new TableColumn("Điểm ĐẾN");
        coladress.setCellValueFactory(new PropertyValueFactory("diemden"));
        /////// add nút
        TableColumn colBtn = new TableColumn();
        colBtn.setCellFactory(evt -> {

            Button btnXoa = new Button("Xóa");
            btnXoa.setOnAction(e -> {
                // Xuử lí Huy Ve
                Alert xacnhanXoaCx = MessageBox.getBox("Xóa Tuyến Đường", "Bạn Có Chắc muốn xóa", Alert.AlertType.CONFIRMATION);
                xacnhanXoaCx.showAndWait().ifPresent(respon -> {
                    if (respon == ButtonType.OK) {
                        Button b = (Button) e.getSource();
                        TableCell cellv = (TableCell) b.getParent();
                        TuyenDuong v = (TuyenDuong) cellv.getTableRow().getItem();

                        try {
                            if (tds.deleteTuyenduong(v.getId())) {
                                MessageBox.getBox("Xóa Tuyến Đường", "Thành Công", Alert.AlertType.INFORMATION).show();
                                this.loadTableData(null);
                            } else {
                                MessageBox.getBox("Xóa Tuyến Đường", "Faild", Alert.AlertType.INFORMATION).show();
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
                Alert xacNhanDoiVe = MessageBox.getBox("Cập nhật !!!!", "BẠN CÓ CHẮC MUỐN Cập nhật Xóa Tuyến Đường NÀY ", Alert.AlertType.CONFIRMATION);
                xacNhanDoiVe.showAndWait().ifPresent(respon -> {
                    if (respon == ButtonType.OK) {

                        Button b = (Button) e.getSource();
                        TableCell cellv = (TableCell) b.getParent();
                        TuyenDuong v = (TuyenDuong) cellv.getTableRow().getItem();

                        TuyenDuong addbx = new TuyenDuong(v.getId(), this.txtDiemDi.getText(), this.txtDiemDen.getText());
                        try {
                            if (tds.updateTuyenDuong(addbx)) {
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
        this.tbvtd.getColumns().addAll(colName, coladress, colBtn, colBtn2); // sửa đổi tên của TableView ở đây
    }

    private void loadTableData(String kw) throws SQLException {
      
        List<TuyenDuong> listtd = tds.getLTuyenDuong(kw);
        this.tbvtd.getItems().clear();
        this.tbvtd.setItems(FXCollections.observableList(listtd));
    }

    public void addTuyenDuongHandler() throws SQLException {

        btnThem.setOnAction(e -> {

            // Xuử lí Huy Ve
            Alert xacnhanXoaCx = MessageBox.getBox("Thêm Tuyến đường", "Bạn Có Chắc muốn Them Những thông tin hiện tại", Alert.AlertType.CONFIRMATION);
            xacnhanXoaCx.showAndWait().ifPresent(respon -> {
                if (respon == ButtonType.OK) {

                    TuyenDuong addbx = new TuyenDuong(this.txtDiemDi.getText(), this.txtDiemDen.getText());
                    try {
                        if (tds.insertTuyenDuong(addbx)) {
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
