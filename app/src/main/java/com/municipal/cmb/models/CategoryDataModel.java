package com.municipal.cmb.models;

/**
 * Created by RazR on 7/1/2018.
 */

public class CategoryDataModel {
    private int CategoryID;
    private String Category;

    public CategoryDataModel(int categoryID, String category) {
        CategoryID = categoryID;
        Category = category;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
