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

import com.example.studycalendar.database.StudentTask;
import com.example.studycalendar.database.StudentTaskDatabase;

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
        StudentTaskDatabase db = StudentTaskDatabase.getInstance(this);
        setEditTextsPreviousInfo(taskName, taskDesc, db);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonEditTaskClick(taskName, taskDesc, taskDueDate, date1, switchIsTest, db);
            }
        });
    }

    public void onButtonEditTaskClick(EditText taskName, EditText taskDesc, CalendarView taskDueDate,
                                      Date date1, Switch switchIsTest, StudentTaskDatabase db) {
        String name = taskName.getText().toString();
        String desc = taskDesc.getText().toString();
        boolean isTest = switchIsTest.isChecked();

        AsyncTask.execute(() -> db.activityDao().deleteActivity(db.activityDao().getActivityList()
                .get(getIntent().getExtras().getInt("id"))));
        StudentTask task = new StudentTask(name, desc, isTest ? "Test" : "Homework",
                date1.getTime(), new Date(date1.getTime() + (1000 * (60 * 60) * 12)).getTime());
        AsyncTask.execute(() -> db.activityDao().insertActivity(task));
        notifyOfTheUpdatedTask(task);
        Intent i = new Intent(EditTaskActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        recreate();
    }

    private void setEditTextsPreviousInfo(EditText taskName, EditText taskDesc, StudentTaskDatabase db) {
        AsyncTask.execute(() -> taskName.setText(db.activityDao().getActivityList()
                .get(getIntent().getExtras().getInt("id")).getSubjectName()));
        AsyncTask.execute(() -> taskDesc.setText(db.activityDao().getActivityList()
                .get(getIntent().getExtras().getInt("id")).getDescription()));
    }

    private void notifyOfTheUpdatedTask(StudentTask task) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "151")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Task Updated")
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