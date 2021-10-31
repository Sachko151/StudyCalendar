package com.example.studycalendar.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "studentactivity")
public class StudentActivity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "subjectName")
    private String subjectName;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "exerciseType")
    private String exerciseType;
    @ColumnInfo(name = "dueDate")
    private String dueDate;
    @ColumnInfo(name = "expiryDate")
    private String expiryDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setNewInstance(StudentActivity task) {
        this.id = task.getId();
        this.subjectName = task.getSubjectName();
        this.description = task.getDescription();
        this.exerciseType = task.getExerciseType();
        this.dueDate = task.getDueDate();
        this.expiryDate = task.getExpiryDate();
    }


    public StudentActivity(int id, String subjectName, String description, String exerciseType, String dueDate, String expiryDate) {
        this.id = id;
        this.subjectName = subjectName;
        this.description = description;
        this.exerciseType = exerciseType;
        this.dueDate = dueDate;
        this.expiryDate = expiryDate;
    }

    @Ignore
    public StudentActivity(String subjectName, String description, String exerciseType, String dueDate, String expiryDate) {
        this.subjectName = subjectName;
        this.description = description;
        this.exerciseType = exerciseType;
        this.dueDate = dueDate;
        this.expiryDate = expiryDate;
    }
}
