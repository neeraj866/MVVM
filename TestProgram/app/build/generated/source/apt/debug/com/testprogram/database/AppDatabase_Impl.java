package com.testprogram.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class AppDatabase_Impl extends AppDatabase {
  private volatile PurchaseDao _purchaseDao;

  private volatile CountryDao _countryDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `purchase_table` (`id` INTEGER NOT NULL, `cost` REAL NOT NULL, `item_id` TEXT, `manufacture` TEXT, `building_id` INTEGER, `item_category_id` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `country_table` (`id` INTEGER NOT NULL, `building_id` INTEGER, `building_name` TEXT, `country` TEXT, `city` TEXT, `state` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"5fa70f5ac5a27daf08bca605098199bd\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `purchase_table`");
        _db.execSQL("DROP TABLE IF EXISTS `country_table`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsPurchaseTable = new HashMap<String, TableInfo.Column>(6);
        _columnsPurchaseTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsPurchaseTable.put("cost", new TableInfo.Column("cost", "REAL", true, 0));
        _columnsPurchaseTable.put("item_id", new TableInfo.Column("item_id", "TEXT", false, 0));
        _columnsPurchaseTable.put("manufacture", new TableInfo.Column("manufacture", "TEXT", false, 0));
        _columnsPurchaseTable.put("building_id", new TableInfo.Column("building_id", "INTEGER", false, 0));
        _columnsPurchaseTable.put("item_category_id", new TableInfo.Column("item_category_id", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPurchaseTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPurchaseTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPurchaseTable = new TableInfo("purchase_table", _columnsPurchaseTable, _foreignKeysPurchaseTable, _indicesPurchaseTable);
        final TableInfo _existingPurchaseTable = TableInfo.read(_db, "purchase_table");
        if (! _infoPurchaseTable.equals(_existingPurchaseTable)) {
          throw new IllegalStateException("Migration didn't properly handle purchase_table(com.testprogram.model.PurchasesModel).\n"
                  + " Expected:\n" + _infoPurchaseTable + "\n"
                  + " Found:\n" + _existingPurchaseTable);
        }
        final HashMap<String, TableInfo.Column> _columnsCountryTable = new HashMap<String, TableInfo.Column>(6);
        _columnsCountryTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsCountryTable.put("building_id", new TableInfo.Column("building_id", "INTEGER", false, 0));
        _columnsCountryTable.put("building_name", new TableInfo.Column("building_name", "TEXT", false, 0));
        _columnsCountryTable.put("country", new TableInfo.Column("country", "TEXT", false, 0));
        _columnsCountryTable.put("city", new TableInfo.Column("city", "TEXT", false, 0));
        _columnsCountryTable.put("state", new TableInfo.Column("state", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCountryTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCountryTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCountryTable = new TableInfo("country_table", _columnsCountryTable, _foreignKeysCountryTable, _indicesCountryTable);
        final TableInfo _existingCountryTable = TableInfo.read(_db, "country_table");
        if (! _infoCountryTable.equals(_existingCountryTable)) {
          throw new IllegalStateException("Migration didn't properly handle country_table(com.testprogram.model.CountriesModel).\n"
                  + " Expected:\n" + _infoCountryTable + "\n"
                  + " Found:\n" + _existingCountryTable);
        }
      }
    }, "5fa70f5ac5a27daf08bca605098199bd", "0060cb9785421179b6416664d1c970c6");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "purchase_table","country_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `purchase_table`");
      _db.execSQL("DELETE FROM `country_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public PurchaseDao getPurchaseDao() {
    if (_purchaseDao != null) {
      return _purchaseDao;
    } else {
      synchronized(this) {
        if(_purchaseDao == null) {
          _purchaseDao = new PurchaseDao_Impl(this);
        }
        return _purchaseDao;
      }
    }
  }

  @Override
  public CountryDao getCountriesDao() {
    if (_countryDao != null) {
      return _countryDao;
    } else {
      synchronized(this) {
        if(_countryDao == null) {
          _countryDao = new CountryDao_Impl(this);
        }
        return _countryDao;
      }
    }
  }
}
