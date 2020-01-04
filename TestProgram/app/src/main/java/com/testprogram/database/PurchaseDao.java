package com.testprogram.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.testprogram.model.PurchasesModel;

import java.util.List;
//this class containing all the methods to define the operations to be performed on data
@Dao
public interface PurchaseDao {

    /*
     * Inserting data in purchase_table
     * @param purchasesModel , object to be inserted
     */
    @Insert
    void insert(PurchasesModel purchasesModel);

    /*
     * Fetching all rows from purchase_table
     */
    @Query("SELECT * FROM purchase_table")
    List<PurchasesModel> getPurchaseItems();

    /*
     * Fetching distinct manufacture names from purchase_table
     */
    @Query("SELECT DISTINCT manufacture FROM purchase_table")
    List<String> getManufacturers();

    /*
     * Fetching distinct item_category_id from purchase_table
     */
    @Query("SELECT DISTINCT item_category_id FROM purchase_table")
    List<String> getItemCategory();

    /*
     * Fetching distinct item_id from purchase_table
     */
    @Query("SELECT DISTINCT item_id FROM purchase_table")
    List<String> getItemId();

    /*
     * Calculating sum of all sales regarding every manufacturer
     * @param manufacture , name of the manufacture
     */
    @Query("SELECT SUM(cost) FROM purchase_table WHERE manufacture = :manufacture")
    double getManufacturerTotal(String manufacture);

    /*
     * Calculating sum of all sales regarding every item_category_id
     *  @param item_category_id , category id of the item
     */
    @Query("SELECT SUM(cost) FROM purchase_table WHERE item_category_id= :item_category_id")
    double getItemCategoryTotal(String item_category_id);

    /*
     * Calculating quantity  of all sales regarding every item_id
     */
    @Query("SELECT COUNT(cost) FROM purchase_table WHERE item_id= :item_id")
    int getItemIdQuantity(String item_id);

    /*
     * Calculating sum of all sales regarding every country
     */
    @Query("SELECT SUM(cost) FROM purchase_table GROUP BY building_id = (SELECT building_id FROM country_table WHERE country = :country)")
    double getCountriesTotal(String country);

    /*
     * Calculating sum of all sales regarding every state
     */
    @Query("SELECT SUM(cost) FROM purchase_table WHERE building_id = (SELECT building_id FROM country_table WHERE state = :state)")
    double getStateTotal(String state);

    /*
     * Fetching building name which is having the most sales
     */
    @Query("SELECT building_id, SUM(cost) FROM purchase_table GROUP BY building_id HAVING SUM(cost) = (SELECT MAX(total) FROM (SELECT SUM(cost) total FROM purchase_table GROUP BY building_id))")
    int getBuildingId();
}
