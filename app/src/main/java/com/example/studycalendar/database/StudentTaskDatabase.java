package com.example.studycalendar.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = StudentTask.class, exportSchema = false, version = 1)
public abstract class StudentTaskDatabase extends RoomDatabase {
    private static final String DB_NAME = "studentactivity_db";
    private static StudentTaskDatabase instance;

    public static synchronized StudentTaskDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    StudentTaskDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract StudentTaskDao activityDao();
}
