package com.example.gradecalculator;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController implements Initializable {

    @FXML
    private TextField categoryName;

    @FXML
    private TextField categoryWeight;

    @FXML
    private Label categoryDisplay;

    @FXML
    private ComboBox<String> comboBox1;

    @FXML
    private ComboBox<String> comboBox2;

    @FXML
    private ComboBox<String> comboBox3;

    @FXML
    private TextField gradeName1;

    @FXML
    private TextField gradeName2;

    @FXML
    private TextField gradeName3;

    @FXML
    private TextField score1;

    @FXML
    private TextField score2;

    @FXML
    private TextField score3;

    @FXML
    private Label finalGrade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        comboBox.setItems(FXCollections.observableArrayList("Final", "Project"));
    }

    @FXML
    void addInput(ActionEvent event) {
        if (!categoryName.getText().isEmpty() && !categoryWeight.getText().isEmpty()) {
            try {
                int weight = Integer.parseInt(categoryWeight.getText());
                if (weight > 0 && weight <= 100) {
                    if (!comboBox1.getItems().stream().anyMatch(item -> item.contains(categoryName.getText()))) {
                        comboBox1.getItems().add(String.format("%s (%s%%)", categoryName.getText(), categoryWeight.getText()));
                        comboBox2.getItems().add(String.format("%s (%s%%)", categoryName.getText(), categoryWeight.getText()));
                        comboBox3.getItems().add(String.format("%s (%s%%)", categoryName.getText(), categoryWeight.getText()));
                        categoryDisplay.setText("");
                        categoryDisplay.setText(String.format("Category \"%s\" is added to the list.", categoryName.getText()));
                    } else {
                        categoryDisplay.setText(String.format("Category \"%s\" is already in the list.", categoryName.getText()));
                    }
                } else {
                    categoryDisplay.setText("Category weight must be between 1 and 100!");
                }
            } catch (NumberFormatException e) {
                categoryDisplay.setText("Category weight must be an integer!");
            }
        } else {
            categoryDisplay.setText("Please input both Name and Weight");
        }
    }

    @FXML
    void deleteInput(ActionEvent event) {
        if (comboBox1.getItems().stream().anyMatch(item -> item.contains(categoryName.getText()))) {
            comboBox1.getItems().removeIf(item -> item.contains(categoryName.getText()));
            comboBox2.getItems().removeIf(item -> item.contains(categoryName.getText()));
            comboBox3.getItems().removeIf(item -> item.contains(categoryName.getText()));
            categoryDisplay.setText("");
            categoryDisplay.setText(String.format("Category \"%s\" is removed from the list.", categoryName.getText()));
        }
    }

    @FXML
    void addRow(ActionEvent event) {

    }

    @FXML
    void calculate(ActionEvent event) {
        finalGrade.setText("100");
    }

    @FXML
    void clear(ActionEvent event) {
        categoryName.clear();
        categoryWeight.clear();
        categoryDisplay.setText("No Action!");
        gradeName1.clear();
        gradeName2.clear();
        gradeName3.clear();
        comboBox1.getItems().clear();
        comboBox2.getItems().clear();
        comboBox3.getItems().clear();
        score1.clear();
        score2.clear();
        score3.clear();
        finalGrade.setText("");
    }
}
