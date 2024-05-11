package com.example.gradecalculator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GradeDetailController implements Initializable {

    @FXML
    private Label finalResult;

    @FXML
    private Label category1;

    @FXML
    private Label category2;

    @FXML
    private Label category3;

    @FXML
    private Label category4;

    @FXML
    private Label category5;

    @FXML
    private Label grade1;

    @FXML
    private Label grade2;

    @FXML
    private Label grade3;

    @FXML
    private Label grade4;

    @FXML
    private Label grade5;

    @FXML
    private Label weight1;

    @FXML
    private Label weight2;

    @FXML
    private Label weight3;

    @FXML
    private Label weight4;

    @FXML
    private Label weight5;

    private Label[] categoryList;
    private Label[] gradeList;
    private Label[] weightList;

    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Double> eachFinalGradeList = new ArrayList<>();
    private double result = 0;


    public void setCategories(ArrayList<Category> categories, ArrayList<Double> eachFinalGradeList, double result) {
        if (categories.size() == eachFinalGradeList.size()) {
            for (int i = 0; i < categories.size(); i++) {
                categoryList[i].setText(categories.get(i).getName());
                gradeList[i].setText(String.format("%.2f", eachFinalGradeList.get(i)));
                weightList[i].setText(Double.toString(categories.get(i).getWeight()));
            }
        }
        finalResult.setText(Double.toString(result));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryList = new Label[]{category1, category2, category3, category4, category5};
        gradeList = new Label[]{grade1, grade2, grade3, grade4, grade5};
        weightList = new Label[]{weight1, weight2, weight3, weight4, weight5};
        setCategories(categories, eachFinalGradeList, result);
    }

}
