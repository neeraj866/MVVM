package com.testprogram.response;

public class Purchases {
    private double cost;

    private String item_id;

    private String item_category_id;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getItemId() {
        return item_id;
    }

    public void setItemId(String item_id) {
        this.item_id = item_id;
    }

    public String getItemCategoryId() {
        return item_category_id;
    }

    public void setItemCategoryId(String item_category_id) {
        this.item_category_id = item_category_id;
    }

    @Override
    public String toString() {
        return String.valueOf(getItemCategoryId());
    }
}