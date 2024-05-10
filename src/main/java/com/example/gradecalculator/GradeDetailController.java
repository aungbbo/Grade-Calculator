package com.example.gradecalculator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GradeDetailController implements Initializable {

    @FXML
    private GridPane finalGridPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        finalGridPane.getStyleClass().add("grid-pane-with-border");
    }
}


