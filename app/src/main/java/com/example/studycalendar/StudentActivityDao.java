package com.example.studycalendar;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentActivityDao {
    @Query("Select * from studentactivity")
    List<StudentActivity> getActivityList();

    @Insert
    void insertActivity(StudentActivity activity);

    @Update
    void updateActivity(StudentActivity activity);

    @Delete
    void deleteActivity(StudentActivity activity);
}
