package com.example.gradecalculator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

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

    private ArrayList<ComboBox<String>> comboBoxes = new ArrayList<>();
    private ArrayList<TextField> gradeNames = new ArrayList<>();
    private ArrayList<TextField> scores = new ArrayList<>();

    private ArrayList<Category> categories;
    private ArrayList<Grade> grades;
    private ArrayList<Double> eachFinalGradeList;
    private double finalResult;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gradeNames.addAll(Arrays.asList(gradeName1, gradeName2, gradeName3, gradeName4, gradeName5, gradeName6, gradeName7));
        comboBoxes.addAll(Arrays.asList(comboBox1, comboBox2, comboBox3, comboBox4, comboBox5, comboBox6, comboBox7));
        scores.addAll(Arrays.asList(score1, score2, score3, score4, score5, score6, score7));

        categories = new ArrayList<>();
        grades = new ArrayList<>();
        eachFinalGradeList = new ArrayList<>();
    }


    @FXML
    public void addInput(ActionEvent event) {
        categoryDisplay.setStyle("-fx-text-fill: red");
        // check for empty name or weight input
        if (!categoryName.getText().isEmpty() && !categoryWeight.getText().isEmpty()) {
            try {
                int weight = Integer.parseInt(categoryWeight.getText().trim());
                // validate weight range (1-100)
                if (weight > 0 && weight <= 100) {
                    categoryDisplay.setStyle("-fx-text-fill: green");
                    // check if category already exists
                    if (!comboBox1.getItems().stream().anyMatch(item -> item.contains(categoryName.getText()))) {
                        // add category to all ComboBoxes with formatting
                        for (ComboBox<String> c : comboBoxes) {
                            c.getItems().add(String.format("%s (%s%%)", categoryName.getText(), categoryWeight.getText()));
                        }
                        categoryDisplay.setText(String.format("Category \"%s\" is added to the list.", categoryName.getText()));
                        // add category object to the categories ArrayList
                        categories.add(new Category(categoryName.getText().trim(), weight));
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
    public void deleteInput(ActionEvent event) {
        // check if the category name exists in any ComboBox item
        if (comboBox1.getItems().stream().anyMatch(item -> item.contains(categoryName.getText()))) {
            // remove the category from all ComboBoxes using stream and filter
            comboBoxes.stream().forEach(c -> c.getItems().removeIf(item -> item.contains(categoryName.getText())));

            // update category display with success message
            categoryDisplay.setStyle("-fx-text-fill: green");
            categoryDisplay.setText(String.format("Category \"%s\" is removed from the list.", categoryName.getText()));

            // remove the category from the categories ArrayList
            for (int i = categories.size() - 1; i >= 0; i--) {
                if (categories.get(i).getName().equals(categoryName.getText())) {
                    categories.remove(i);
                }
            }
        }
    }


    private void updateComboBox(ComboBox<String> c) {
        for (Category category : categories) {
            c.getItems().add(String.format("%s (%s%%)", category.getName(), category.getWeight()));
        }
    }


    private int getNumberOfRows() {
        if (gradesGridPane.getRowConstraints().isEmpty()) {
            return 0;
        }
        return gradesGridPane.getRowConstraints().size();
    }


    @FXML
    public void addRow(ActionEvent event) {
        // get the current number of rows in the GridPane
        int rowIndex = getNumberOfRows();

        // create a new TextField for entering the grade name
        TextField newGradeName = new TextField();
        newGradeName.getStyleClass().add("new-grade-name");
        gradesGridPane.setMargin(newGradeName, new Insets(2, 2, 2, 2));
        gradeNames.add(newGradeName);

        // create a new ComboBox for selecting options
        ComboBox newComboBox = new ComboBox();
        newComboBox.getStyleClass().add("new-combo-box");
        gradesGridPane.setMargin(newComboBox, new Insets(2, 2, 2, 2));
        comboBoxes.add(newComboBox);
        updateComboBox(newComboBox);

        // create a new TextField for entering the score
        TextField newScore = new TextField();
        newScore.getStyleClass().add("new-score");
        gradesGridPane.setMargin(newScore, new Insets(2, 2, 2, 2));
        scores.add(newScore);

        // set constraints to position the new elements in the GridPane
        GridPane.setConstraints(newGradeName, 0, rowIndex + 1);
        GridPane.setConstraints(newComboBox, 1, rowIndex + 1);
        GridPane.setConstraints(newScore, 2, rowIndex + 1);

        // add the newly created elements to the GridPane children
        gradesGridPane.getChildren().addAll(newGradeName, newComboBox, newScore);
        gradesGridPane.getRowConstraints().add(new RowConstraints());
    }


    private void getGradeInput() {
        for (int i = 0; i < getNumberOfRows() - 1; i++) {
            // get the grade name and score from the corresponding TextFields in the ArrayLists
            String name = gradeNames.get(i).getText().trim();
            String scoreString = scores.get(i).getText().trim();

            // process the grade information only if a score is entered
            if (scoreString != null && !scoreString.isEmpty()) {
                try {
                    double score = Double.parseDouble(scoreString);
                    String selectedItem = comboBoxes.get(i).getSelectionModel().getSelectedItem();
                    // process only if there's a selection
                    if (selectedItem != null) {
                        int index1 = selectedItem.indexOf(" (");
                        int index2 = selectedItem.indexOf("%");
                        Category category = new Category(selectedItem.substring(0, index1), Integer.parseInt(selectedItem.substring(index1 + 2, index2)));
                        // add the Grade object to the grades ArrayList
                        grades.add(new Grade(category, name, score));
                    }
                } catch (NumberFormatException e) {
                    categoryDisplay.setStyle("-fx-text-fill: red");
                    categoryDisplay.setText("Score must be a whole number or a decimal number!");
                }
            }
        }
    }


    private double getEachCategoryFinalGrade(Category category) {
        double gradeSum = 0;
        int count = 0;
        // calculate final grade for each category
        for (Grade grade : grades) {
            if (grade.getCategory().getName().equals(category.getName())) {
                gradeSum += grade.getScore();
                count++;
            }
        }
        double eachFinalGrade = gradeSum / (count * (100.0 / category.getWeight()));
        return eachFinalGrade;
    }


    @FXML
    public void calculate(ActionEvent event) {
        // remove all items from grades Arraylist so that the data are not duplicating
        grades.clear();
        eachFinalGradeList.clear();

        // get user input data and store them to respective ArrayList
        getGradeInput();

        // calculate total final grade
        int weightSum = 0;
        finalResult = 0;
        for (Category category : categories) {
            weightSum += category.getWeight();
            double eachFinalGrade = getEachCategoryFinalGrade(category);
            eachFinalGradeList.add(eachFinalGrade);
            finalResult += eachFinalGrade;
        }

        // check if total combined category weight is 100%
        if (weightSum != 100) {
            categoryDisplay.setStyle("-fx-text-fill: red");
            categoryDisplay.setText("Total weight should be 100%!");
        }
        finalGrade.setText(String.format("%.2f", finalResult));
    }


    @FXML
    public void clear(ActionEvent event) {
        categoryDisplay.setStyle("");

        // clear all inputs
        categoryName.clear();
        categoryWeight.clear();
        categoryDisplay.setText("No Action!");
        for (TextField gn : gradeNames) { gn.clear(); }
        for (TextField s : scores) { s.clear(); }
        for (ComboBox<String> c : comboBoxes) { c.getItems().clear(); }
        finalResult = 0;
        finalGrade.setText("0.0");

        // remove all items from both ArrayLists
        categories.clear();
        grades.clear();
    }


    @FXML
    public void showDetails(ActionEvent event) {
        // call "grade-detail" page when click "Details" button
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/grade-detail.fxml"));
            Parent root = loader.load();
            GradeDetailController detailController = loader.getController();
            detailController.setCategories(categories, eachFinalGradeList, finalResult );
            Stage detailStage = new Stage();
            detailStage.setScene(new Scene(root));
            detailStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
