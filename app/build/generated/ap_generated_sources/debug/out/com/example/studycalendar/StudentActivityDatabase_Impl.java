package com.example.studycalendar;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class StudentActivityDatabase_Impl extends StudentActivityDatabase {
  private volatile StudentActivityDao _studentActivityDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `studentactivity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `subjectName` TEXT, `description` TEXT, `exerciseType` TEXT, `dueDate` TEXT, `expiryDate` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'bfc7a004ebda2a72f12189a871520093')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `studentactivity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
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
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsStudentactivity = new HashMap<String, TableInfo.Column>(6);
        _columnsStudentactivity.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudentactivity.put("subjectName", new TableInfo.Column("subjectName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudentactivity.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudentactivity.put("exerciseType", new TableInfo.Column("exerciseType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudentactivity.put("dueDate", new TableInfo.Column("dueDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStudentactivity.put("expiryDate", new TableInfo.Column("expiryDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStudentactivity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStudentactivity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStudentactivity = new TableInfo("studentactivity", _columnsStudentactivity, _foreignKeysStudentactivity, _indicesStudentactivity);
        final TableInfo _existingStudentactivity = TableInfo.read(_db, "studentactivity");
        if (! _infoStudentactivity.equals(_existingStudentactivity)) {
          return new RoomOpenHelper.ValidationResult(false, "studentactivity(com.example.studycalendar.StudentActivity).\n"
                  + " Expected:\n" + _infoStudentactivity + "\n"
                  + " Found:\n" + _existingStudentactivity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "bfc7a004ebda2a72f12189a871520093", "171a2b9716482e3c1aaa1c99568e3b1e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "studentactivity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `studentactivity`");
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
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(StudentActivityDao.class, StudentActivityDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public StudentActivityDao activityDao() {
    if (_studentActivityDao != null) {
      return _studentActivityDao;
    } else {
      synchronized(this) {
        if(_studentActivityDao == null) {
          _studentActivityDao = new StudentActivityDao_Impl(this);
        }
        return _studentActivityDao;
      }
    }
  }
}
