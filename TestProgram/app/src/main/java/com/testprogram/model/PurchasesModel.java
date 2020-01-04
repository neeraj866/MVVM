package com.testprogram.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
//This class annotated with the @Entity annotation is mapped to a purchase table in database and every field in this class represents the column name.
@Entity(tableName = "purchase_table")
public class PurchasesModel {

    @PrimaryKey
    @NonNull
    private int id;

    public double cost;
    public String item_id;
    public String manufacture;
    public Integer building_id;

    public String item_category_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public Integer getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Integer building_id) {
        this.building_id = building_id;
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