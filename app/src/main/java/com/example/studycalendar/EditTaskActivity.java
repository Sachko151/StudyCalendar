package com.example.studycalendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Date;

public class EditTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        final EditText taskName = findViewById(R.id.etEditAddTaskName);
        final EditText taskDesc = findViewById(R.id.etEditAddTaskDesc);
        final CalendarView taskDueDate = findViewById(R.id.etEditAddDueDate);
        Date date1 = new Date();
        taskDueDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date1.setYear(i);
                date1.setMonth(i1);
                date1.setDate(i2);
            }
        });
        final Switch switchIsTest = findViewById(R.id.swEditAddIsTest);
        Button btnAction = findViewById(R.id.btnEditAddTask);
        StudentActivityDatabase db = StudentActivityDatabase.getInstance(this);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonEditTaskClick(taskName, taskDesc, taskDueDate, date1, switchIsTest, db);
            }
        });
    }

    public void onButtonEditTaskClick(EditText taskName, EditText taskDesc, CalendarView taskDueDate,
                                      Date date1, Switch switchIsTest, StudentActivityDatabase db) {
        String name = taskName.getText().toString();
        String desc = taskDesc.getText().toString();
        boolean isTest = switchIsTest.isChecked();

        AsyncTask.execute(() -> db.activityDao().deleteActivity(db.activityDao().getActivityList()
                .get(getIntent().getExtras().getInt("id"))));
        StudentActivity task = new StudentActivity(name, desc, isTest ? "Test" : "Homework",
                date1.toString().substring(0, 10), date1.toString());
        AsyncTask.execute(() -> db.activityDao().insertActivity(task));
        Intent i = new Intent(EditTaskActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        recreate();
    }
}