module com.example.javahospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javahospital to javafx.fxml;
    exports com.example.javahospital;
}