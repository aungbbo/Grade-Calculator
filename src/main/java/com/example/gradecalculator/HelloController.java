package com.example.gradecalculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

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
    private ComboBox<String> comboBox4;

    @FXML
    private ComboBox<String> comboBox5;

    @FXML
    private ComboBox<String> comboBox6;

    @FXML
    private ComboBox<String> comboBox7;

    @FXML
    private TextField gradeName1;

    @FXML
    private TextField gradeName2;

    @FXML
    private TextField gradeName3;

    @FXML
    private TextField gradeName4;

    @FXML
    private TextField gradeName5;

    @FXML
    private TextField gradeName6;

    @FXML
    private TextField gradeName7;

    @FXML
    private TextField score1;

    @FXML
    private TextField score2;

    @FXML
    private TextField score3;

    @FXML
    private TextField score4;

    @FXML
    private TextField score5;

    @FXML
    private TextField score6;

    @FXML
    private TextField score7;

    @FXML
    private Label finalGrade;

    @FXML
    private GridPane gradesGridPane;

    private ComboBox<String>[] comboBoxes;
    private TextField[] gradeNames;
    private TextField[] scores;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gradeNames = new TextField[]{gradeName1, gradeName2, gradeName3, gradeName4, gradeName5, gradeName6, gradeName7};
        comboBoxes = new ComboBox[]{comboBox1, comboBox2, comboBox3, comboBox4, comboBox5, comboBox6, comboBox7};
        scores = new TextField[]{score1, score2, score3, score4, score5, score6, score7};

//        comboBox.setItems(FXCollections.observableArrayList("Final", "Project"));
    }

    @FXML
    void addInput(ActionEvent event) {
        if (!categoryName.getText().isEmpty() && !categoryWeight.getText().isEmpty()) {
            try {
                int weight = Integer.parseInt(categoryWeight.getText());
                if (weight > 0 && weight <= 100) {
                    if (!comboBox1.getItems().stream().anyMatch(item -> item.contains(categoryName.getText()))) {
                        for (ComboBox<String> c : comboBoxes) {
                            c.getItems().add(String.format("%s (%s%%)", categoryName.getText(), categoryWeight.getText()));
                        }
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
            for (ComboBox<String> c : comboBoxes) {
                c.getItems().removeIf(item -> item.contains(categoryName.getText()));
            }
            categoryDisplay.setText("");
            categoryDisplay.setText(String.format("Category \"%s\" is removed from the list.", categoryName.getText()));
        }
    }


    public int getNumberOfRows() {
        if (gradesGridPane.getRowConstraints().isEmpty()) {
            return 0;
        }
        return gradesGridPane.getRowConstraints().size();
    }

    @FXML
    void addRow(ActionEvent event) {
        int rowIndex = getNumberOfRows();

        TextField newGradeName = new TextField();
        newGradeName.setStyle("-fx-min-height: 31; -fx-max-height: 31px; -fx-min-width: 194px; -fx-max-width: 194px; -fx-margin-left: 2px");

        ComboBox newComboBox = new ComboBox();
        newComboBox.setStyle("-fx-min-height: 31px; -fx-min-width: 164; -fx-margin: 2px");

        TextField newScore = new TextField();
        newScore.setStyle("-fx-min-height: 31px; -fx-margin: 2px");

        GridPane.setConstraints(newGradeName, 0, rowIndex + 1);
        GridPane.setConstraints(newComboBox, 1, rowIndex + 1);
        GridPane.setConstraints(newScore, 2, rowIndex + 1);

        gradesGridPane.getChildren().addAll(newGradeName, newComboBox, newScore);
        RowConstraints rowConstraints = new RowConstraints(35);
//        rowConstraints.setMinHeight(35);
        gradesGridPane.getRowConstraints().add(rowConstraints);
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
        for (TextField gn : gradeNames) { gn.clear(); }
        for (TextField s : scores) { s.clear(); }
        for (ComboBox<String> c : comboBoxes) { c.getItems().clear(); }
        finalGrade.setText("");
    }
}
