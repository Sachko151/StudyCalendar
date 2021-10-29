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

public class AddTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);
        final Button actionBtn = findViewById(R.id.btnAddTask);
        final EditText taskName = findViewById(R.id.etAddTaskName);
        final EditText taskDesc = findViewById(R.id.etAddTaskDesc);
        final CalendarView taskDueDate = findViewById(R.id.cvDueDate);
        Date date1 = new Date();
        taskDueDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date1.setYear(i);
                date1.setMonth(i1);
                date1.setDate(i2);
            }
        });
        final Switch switchIsTest = findViewById(R.id.swAddIsTest);
        StudentActivityDatabase db = StudentActivityDatabase.getInstance(this);
        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonAddTaskClick(taskName, taskDesc, taskDueDate, date1, db, switchIsTest);
            }
        });
    }

    public void ButtonAddTaskClick(EditText taskName, EditText taskDesc, CalendarView taskDueDate,
                                   Date date1, StudentActivityDatabase db, Switch switchIsTest) {
        String name = taskName.getText().toString();
        String desc = taskDesc.getText().toString();
        boolean isTest = switchIsTest.isChecked();
        StudentActivity task = new StudentActivity(name, desc,
                isTest ? "Test" : "Homework", date1.toString().substring(0, 10), date1.toString());
        AsyncTask.execute(() -> db.activityDao().insertActivity(task));
        Intent i = new Intent(AddTestActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        recreate();
    }
}