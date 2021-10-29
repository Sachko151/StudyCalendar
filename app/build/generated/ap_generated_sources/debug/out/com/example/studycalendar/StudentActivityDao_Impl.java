package com.example.studycalendar;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class StudentActivityDao_Impl implements StudentActivityDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StudentActivity> __insertionAdapterOfStudentActivity;

  private final EntityDeletionOrUpdateAdapter<StudentActivity> __deletionAdapterOfStudentActivity;

  private final EntityDeletionOrUpdateAdapter<StudentActivity> __updateAdapterOfStudentActivity;

  public StudentActivityDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStudentActivity = new EntityInsertionAdapter<StudentActivity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `studentactivity` (`id`,`subjectName`,`description`,`exerciseType`,`dueDate`,`expiryDate`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StudentActivity value) {
        stmt.bindLong(1, value.getId());
        if (value.getSubjectName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSubjectName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getExerciseType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getExerciseType());
        }
        if (value.getDueDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDueDate());
        }
        if (value.getExpiryDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getExpiryDate());
        }
      }
    };
    this.__deletionAdapterOfStudentActivity = new EntityDeletionOrUpdateAdapter<StudentActivity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `studentactivity` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StudentActivity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfStudentActivity = new EntityDeletionOrUpdateAdapter<StudentActivity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `studentactivity` SET `id` = ?,`subjectName` = ?,`description` = ?,`exerciseType` = ?,`dueDate` = ?,`expiryDate` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, StudentActivity value) {
        stmt.bindLong(1, value.getId());
        if (value.getSubjectName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getSubjectName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getExerciseType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getExerciseType());
        }
        if (value.getDueDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDueDate());
        }
        if (value.getExpiryDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getExpiryDate());
        }
        stmt.bindLong(7, value.getId());
      }
    };
  }

  @Override
  public void insertActivity(final StudentActivity activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfStudentActivity.insert(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteActivity(final StudentActivity activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfStudentActivity.handle(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateActivity(final StudentActivity activity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfStudentActivity.handle(activity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<StudentActivity> getActivityList() {
    final String _sql = "Select * from studentactivity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfSubjectName = CursorUtil.getColumnIndexOrThrow(_cursor, "subjectName");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfExerciseType = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseType");
      final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
      final int _cursorIndexOfExpiryDate = CursorUtil.getColumnIndexOrThrow(_cursor, "expiryDate");
      final List<StudentActivity> _result = new ArrayList<StudentActivity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final StudentActivity _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpSubjectName;
        if (_cursor.isNull(_cursorIndexOfSubjectName)) {
          _tmpSubjectName = null;
        } else {
          _tmpSubjectName = _cursor.getString(_cursorIndexOfSubjectName);
        }
        final String _tmpDescription;
        if (_cursor.isNull(_cursorIndexOfDescription)) {
          _tmpDescription = null;
        } else {
          _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        }
        final String _tmpExerciseType;
        if (_cursor.isNull(_cursorIndexOfExerciseType)) {
          _tmpExerciseType = null;
        } else {
          _tmpExerciseType = _cursor.getString(_cursorIndexOfExerciseType);
        }
        final String _tmpDueDate;
        if (_cursor.isNull(_cursorIndexOfDueDate)) {
          _tmpDueDate = null;
        } else {
          _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
        }
        final String _tmpExpiryDate;
        if (_cursor.isNull(_cursorIndexOfExpiryDate)) {
          _tmpExpiryDate = null;
        } else {
          _tmpExpiryDate = _cursor.getString(_cursorIndexOfExpiryDate);
        }
        _item = new StudentActivity(_tmpId,_tmpSubjectName,_tmpDescription,_tmpExerciseType,_tmpDueDate,_tmpExpiryDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
