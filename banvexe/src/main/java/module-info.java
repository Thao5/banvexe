module com.thao.banvexe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.thao.banvexe to javafx.fxml;
    exports com.thao.banvexe;
}
