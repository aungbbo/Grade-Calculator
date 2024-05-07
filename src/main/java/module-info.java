module com.example.gradecalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gradecalculator to javafx.fxml;
    exports com.example.gradecalculator;
}