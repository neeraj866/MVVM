package com.testprogram.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.testprogram.model.CountriesModel;
import com.testprogram.model.PurchasesModel;
import com.testprogram.response.CountryResponse;

import java.util.List;
//this class containing all the methods to define the operations to be performed on data
@Dao
public interface CountryDao {

    /*
     * Inserting data in country_table
     */
    @Insert
    void insert(CountriesModel countriesModel);

    /*
     * Fetching all rows from county_table
     */
    @Query("SELECT * FROM country_table")
    List<CountriesModel> getCountries();

    /*
     * Fetching all rows from county_table where country is country
     */
    @Query("SELECT DISTINCT state FROM country_table WHERE country = :country")
    List<String> getStates(String country);

    /*
     * Fetching distinct country names from country_table
     */
    @Query("SELECT DISTINCT country FROM country_table")
    List<String> getCountriesName();

    /*
     * Fetching building name from county_table where building_id is building_id
     */
    @Query("SELECT building_name FROM country_table WHERE building_id = :building_id")
    String getBuildingName(int building_id);
}
