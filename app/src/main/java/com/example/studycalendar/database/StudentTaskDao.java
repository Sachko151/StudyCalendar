package com.example.studycalendar.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentTaskDao {
    @Query("Select * from studentactivity")
    List<StudentTask> getActivityList();

    @Insert
    void insertActivity(StudentTask activity);

    @Update
    void updateActivity(StudentTask activity);

    @Delete
    void deleteActivity(StudentTask activity);
}
