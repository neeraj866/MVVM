package com.testprogram.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.testprogram.model.CountriesModel;
import com.testprogram.model.PurchasesModel;
//this  abstract class annotated with @Database annotation is used to create a database with given name along with database version.
@Database(entities = {PurchasesModel.class, CountriesModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PurchaseDao getPurchaseDao();

    public abstract CountryDao getCountriesDao();
}