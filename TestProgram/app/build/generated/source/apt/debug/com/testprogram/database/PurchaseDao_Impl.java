package com.testprogram.database;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.testprogram.model.PurchasesModel;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class PurchaseDao_Impl implements PurchaseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPurchasesModel;

  public PurchaseDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPurchasesModel = new EntityInsertionAdapter<PurchasesModel>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `purchase_table`(`id`,`cost`,`item_id`,`manufacture`,`building_id`,`item_category_id`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PurchasesModel value) {
        stmt.bindLong(1, value.getId());
        stmt.bindDouble(2, value.cost);
        if (value.item_id == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.item_id);
        }
        if (value.manufacture == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.manufacture);
        }
        if (value.building_id == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.building_id);
        }
        if (value.item_category_id == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.item_category_id);
        }
      }
    };
  }

  @Override
  public void insert(PurchasesModel purchasesModel) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPurchasesModel.insert(purchasesModel);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<PurchasesModel> getPurchaseItems() {
    final String _sql = "SELECT * FROM purchase_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfCost = _cursor.getColumnIndexOrThrow("cost");
      final int _cursorIndexOfItemId = _cursor.getColumnIndexOrThrow("item_id");
      final int _cursorIndexOfManufacture = _cursor.getColumnIndexOrThrow("manufacture");
      final int _cursorIndexOfBuildingId = _cursor.getColumnIndexOrThrow("building_id");
      final int _cursorIndexOfItemCategoryId = _cursor.getColumnIndexOrThrow("item_category_id");
      final List<PurchasesModel> _result = new ArrayList<PurchasesModel>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final PurchasesModel _item;
        _item = new PurchasesModel();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _item.cost = _cursor.getDouble(_cursorIndexOfCost);
        _item.item_id = _cursor.getString(_cursorIndexOfItemId);
        _item.manufacture = _cursor.getString(_cursorIndexOfManufacture);
        if (_cursor.isNull(_cursorIndexOfBuildingId)) {
          _item.building_id = null;
        } else {
          _item.building_id = _cursor.getInt(_cursorIndexOfBuildingId);
        }
        _item.item_category_id = _cursor.getString(_cursorIndexOfItemCategoryId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<String> getManufacturers() {
    final String _sql = "SELECT DISTINCT manufacture FROM purchase_table";
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
  public List<String> getItemCategory() {
    final String _sql = "SELECT DISTINCT item_category_id FROM purchase_table";
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
  public List<String> getItemId() {
    final String _sql = "SELECT DISTINCT item_id FROM purchase_table";
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
  public double getManufacturerTotal(String manufacture) {
    final String _sql = "SELECT SUM(cost) FROM purchase_table WHERE manufacture = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (manufacture == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, manufacture);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final double _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getItemCategoryTotal(String item_category_id) {
    final String _sql = "SELECT SUM(cost) FROM purchase_table WHERE item_category_id= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (item_category_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, item_category_id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final double _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getItemIdQuantity(String item_id) {
    final String _sql = "SELECT COUNT(cost) FROM purchase_table WHERE item_id= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (item_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, item_id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getCountriesTotal(String country) {
    final String _sql = "SELECT SUM(cost) FROM purchase_table GROUP BY building_id = (SELECT building_id FROM country_table WHERE country = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (country == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, country);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final double _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public double getStateTotal(String state) {
    final String _sql = "SELECT SUM(cost) FROM purchase_table WHERE building_id = (SELECT building_id FROM country_table WHERE state = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (state == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, state);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final double _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getDouble(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getBuildingId() {
    final String _sql = "SELECT building_id, SUM(cost) FROM purchase_table GROUP BY building_id HAVING SUM(cost) = (SELECT MAX(total) FROM (SELECT SUM(cost) total FROM purchase_table GROUP BY building_id))";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
