package com.example.studycalendar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.studycalendar.database.StudentActivity;
import com.example.studycalendar.database.StudentActivityDatabase;

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
        notifyOfTheCreatedTask(task);
        Intent i = new Intent(AddTestActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        recreate();
    }

    private void notifyOfTheCreatedTask(StudentActivity task) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "151")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("New Task Added")
                .setContentText(task.getSubjectName() + " " + task.getDescription())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        createNotificationChannel();
        notificationManager.notify(151, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = "sadasdsadsasda";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channel_name), name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}