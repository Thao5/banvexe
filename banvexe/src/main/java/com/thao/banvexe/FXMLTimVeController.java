/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.banvexe;

import com.thao.Services.KhoaBeoVeXeService;
import com.thao.Utils.MessageBox;
import com.thao.pojo.Ve;
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
public class FXMLTimVeController implements Initializable {

    @FXML
    private TextField kwSearch;
    @FXML
    private TableView<Ve> tableVeXe;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private TabPane tabPanel;

    private void LoadTableColunm() {
        this.tableVeXe.getColumns().clear();
        TableColumn colMa = new TableColumn("Mã");
        colMa.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn colCho = new TableColumn("Số Ghế");
        colCho.setCellValueFactory(new PropertyValueFactory("soghe"));

        TableColumn colGiaVe = new TableColumn("Giá Vé");
        colGiaVe.setCellValueFactory(new PropertyValueFactory("giave"));

        TableColumn colNgay = new TableColumn("Ngày in");
        colNgay.setCellValueFactory(new PropertyValueFactory("ngayin"));

        TableColumn colKH = new TableColumn("Khach Hang");
        colKH.setCellValueFactory(new PropertyValueFactory("khachhang"));

        TableColumn colSDT = new TableColumn("SDT");
        colSDT.setCellValueFactory(new PropertyValueFactory("sdt"));

        TableColumn colUser = new TableColumn("User");
        colUser.setCellFactory(new PropertyValueFactory("user_id"));

        TableColumn colChuyenXe = new TableColumn("Chuyến Xe");
        colChuyenXe.setCellFactory(new PropertyValueFactory("chuyenxe_id"));
        TableColumn colBtn = new TableColumn();

        colBtn.setCellFactory(evt -> {

            Button btnHuyVe = new Button("Hủy Vé");
            btnHuyVe.setOnAction(e -> {
                // Xuử lí Huy Ve
                Alert xacNhanHuyVe = MessageBox.getBox("Hủy Vé", "Bạn Có Chắc muốn xóa vé này ", Alert.AlertType.CONFIRMATION);
                xacNhanHuyVe.showAndWait().ifPresent(respon -> {
                    if (respon == ButtonType.OK) {
                        Button b = (Button) e.getSource();
                        TableCell cellv = (TableCell) b.getParent();
                        Ve v = (Ve) cellv.getTableRow().getItem();
                        KhoaBeoVeXeService s = new KhoaBeoVeXeService();
                        try {
                            if (s.deleteVe(v.getId())) {
                                MessageBox.getBox("Hủy Vé", "Thành Công", Alert.AlertType.INFORMATION).show();
                                this.LoadTableData(null);
                            } else {
                                MessageBox.getBox("Hủy Vé", "Faild", Alert.AlertType.INFORMATION).show();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(FXMLTimVeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            });
            TableCell cell = new TableCell();
            cell.setGraphic(btnHuyVe);
            return cell;
        });

        TableColumn colBtn2 = new TableColumn();
        colBtn2.setCellFactory(evt -> {
            Button btnDoiVe = new Button("ĐỔI VÉ !!!!");
            btnDoiVe.setOnAction(e -> {
                Alert xacNhanDoiVe = MessageBox.getBox("ĐỔI VÉ !!!!", "BẠN CÓ CHẮC MUỐN ĐỔI VÉ NÀY ", Alert.AlertType.CONFIRMATION);
                xacNhanDoiVe.showAndWait().ifPresent(respon -> {
                    if (respon == ButtonType.OK) {
                        Stage oldStage = (Stage) btnDoiVe.getScene().getWindow();
                        oldStage.close();
                        Button b = (Button) e.getSource();
                        TableCell cellv = (TableCell) b.getParent();
                        Ve v = (Ve) cellv.getTableRow().getItem();

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDoiVe.fxml"));
                        Parent root1;
                        try {
                            root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(root1));

                            // Lấy Controller của scene mới và truyền đối tượng Ve tới nó
                            FXMLDoiVe doiVeController = fxmlLoader.getController();
                            try {
                                doiVeController.setModel(v);
                            } catch (SQLException ex) {
                                Logger.getLogger(FXMLTimVeController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            stage.show();
                            
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                });
            TableCell cell = new TableCell();
                cell.setGraphic(btnDoiVe);
                return cell;
            });
            ///add vao bang table
            this.tableVeXe.getColumns().addAll(colMa, colCho, colGiaVe, colNgay, colKH, colSDT, colBtn, colBtn2);
                }
                           
    private void LoadTableData(String kw) throws SQLException {
//        this.tableVeXe.getColumns().clear();
        KhoaBeoVeXeService vx = new KhoaBeoVeXeService();
        List<Ve> lsv = vx.getListVe(kw);

        this.tableVeXe.setItems(FXCollections.observableList(lsv));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            this.LoadTableColunm();
            this.LoadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLTimVeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.kwSearch.textProperty().addListener(e -> {
            try {
                if(!isSql(this.kwSearch.getText())){
                this.LoadTableData(this.kwSearch.getText());
                }
            } catch (SQLException ex1) {
                Logger.getLogger(FXMLTimVeController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        });

//        this.tabPanel.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> ov, Tab tab1, Tab tab2) -> {
//            if (tabPanel.getSelectionModel().getSelectedIndex() == 0) {
//                try {
//                    this.LoadTableColunm();
//                    this.LoadTableData(null);
//                } catch (SQLException ex) {
//                    Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                this.kwSearch.textProperty().addListener(d -> {
//                    try {
//                        this.LoadTableData(this.kwSearch.getText());
//                    } catch (SQLException ex) {
//                        Logger.getLogger(FXMLDatVeController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                });
//
//            } else if (tabPanel.getSelectionModel().getSelectedIndex() == 1) {
//                this.tableVeXe.getColumns().clear();
//
//            }
//        });
    }
    public static boolean isSql(String kw) {
        String[] sqlKeywords = {"SELECT", "INSERT", "UPDATE", "DELETE"};
        for (String keyword : sqlKeywords) {
            if (kw.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

}
