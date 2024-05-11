module com.example.gradecalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.naming;
    requires java.sql;


    opens com.example.gradecalculator to javafx.fxml;
    exports com.example.gradecalculator;
}