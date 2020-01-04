package com.testprogram.database;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.testprogram.model.CountriesModel;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class CountryDao_Impl implements CountryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCountriesModel;

  public CountryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCountriesModel = new EntityInsertionAdapter<CountriesModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `country_table`(`id`,`building_id`,`building_name`,`country`,`city`,`state`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CountriesModel value) {
        stmt.bindLong(1, value.getId());
        if (value.getBuilding_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getBuilding_id());
        }
        if (value.getBuilding_name() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getBuilding_name());
        }
        if (value.getCountry() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCountry());
        }
        if (value.getCity() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCity());
        }
        if (value.getState() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getState());
        }
      }
    };
  }

  @Override
  public void insert(CountriesModel countriesModel) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCountriesModel.insert(countriesModel);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<CountriesModel> getCountries() {
    final String _sql = "SELECT * FROM country_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfBuildingId = _cursor.getColumnIndexOrThrow("building_id");
      final int _cursorIndexOfBuildingName = _cursor.getColumnIndexOrThrow("building_name");
      final int _cursorIndexOfCountry = _cursor.getColumnIndexOrThrow("country");
      final int _cursorIndexOfCity = _cursor.getColumnIndexOrThrow("city");
      final int _cursorIndexOfState = _cursor.getColumnIndexOrThrow("state");
      final List<CountriesModel> _result = new ArrayList<CountriesModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CountriesModel _item;
        _item = new CountriesModel();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final Integer _tmpBuilding_id;
        if (_cursor.isNull(_cursorIndexOfBuildingId)) {
          _tmpBuilding_id = null;
        } else {
          _tmpBuilding_id = _cursor.getInt(_cursorIndexOfBuildingId);
        }
        _item.setBuilding_id(_tmpBuilding_id);
        final String _tmpBuilding_name;
        _tmpBuilding_name = _cursor.getString(_cursorIndexOfBuildingName);
        _item.setBuilding_name(_tmpBuilding_name);
        final String _tmpCountry;
        _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
        _item.setCountry(_tmpCountry);
        final String _tmpCity;
        _tmpCity = _cursor.getString(_cursorIndexOfCity);
        _item.setCity(_tmpCity);
        final String _tmpState;
        _tmpState = _cursor.getString(_cursorIndexOfState);
        _item.setState(_tmpState);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getStates(String country) {
    final String _sql = "SELECT DISTINCT state FROM country_table WHERE country = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (country == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, country);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getCountriesName() {
    final String _sql = "SELECT DISTINCT country FROM country_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final List<String> _result = new ArrayList<String>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final String _item;
        _item = _cursor.getString(0);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public String getBuildingName(int building_id) {
    final String _sql = "SELECT building_name FROM country_table WHERE building_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, building_id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
