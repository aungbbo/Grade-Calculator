package com.example.gradecalculator;

public class Category {
    private String categoryName;
    private int categoryWeight;

    // Constructor
    public Category(String categoryName, int categoryWeight) {
        this.categoryName = categoryName;
        this.categoryWeight = categoryWeight;
    }

    // Getters and setters
    public String getName() {
        return categoryName;
    }

    public int getWeight() {
        return categoryWeight;
    }

    public void setName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setWeight(int categoryWeight) {
        this.categoryWeight = categoryWeight;
    }
}
