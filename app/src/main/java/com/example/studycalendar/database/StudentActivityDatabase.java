package com.example.studycalendar.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = StudentActivity.class, exportSchema = false, version = 1)
public abstract class StudentActivityDatabase extends RoomDatabase {
    private static final String DB_NAME = "studentactivity_db";
    private static StudentActivityDatabase instance;

    public static synchronized StudentActivityDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    StudentActivityDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract StudentActivityDao activityDao();
}
