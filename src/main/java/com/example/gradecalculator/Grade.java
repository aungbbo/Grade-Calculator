package com.example.gradecalculator;

public class Grade {
    Category category;
    String name;
    double score;

    // Constructor
    public Grade(Category category, String name, double score) {
        this.category = category;
        this.name = name;
        this.score = score;
    }

    // Getters and setters
    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
