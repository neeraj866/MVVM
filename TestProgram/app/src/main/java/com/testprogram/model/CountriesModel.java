package com.testprogram.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
//This class annotated with the @Entity annotation is mapped to a country table in database and every field in this class represents the column name.
@Entity(tableName = "country_table")
public class CountriesModel {
    @PrimaryKey
    private int id;

    private Integer building_id;
    private String building_name;
    private String country;
    private String city;
    private String state;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Integer building_id) {
        this.building_id = building_id;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return getCountry();
    }
}
