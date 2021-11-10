package com.example.studycalendar;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.studycalendar.database.StudentTask;
import com.example.studycalendar.database.StudentTaskDatabase;

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
                date1.setYear(i - 1900);
                date1.setMonth(i1);
                date1.setDate(i2);
                date1.setHours(1);
                date1.setMinutes(0);
                date1.setSeconds(0);
            }
        });
        final Switch switchIsTest = findViewById(R.id.swAddIsTest);
        StudentTaskDatabase db = StudentTaskDatabase.getInstance(this);
        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonAddTaskClick(taskName, taskDesc, taskDueDate, date1, db, switchIsTest);
            }
        });
    }

    public void ButtonAddTaskClick(EditText taskName, EditText taskDesc, CalendarView taskDueDate,
                                   Date date1, StudentTaskDatabase db, Switch switchIsTest) {
        String name = taskName.getText().toString();
        String desc = taskDesc.getText().toString();
        boolean isTest = switchIsTest.isChecked();
        StudentTask task = new StudentTask(name, desc,
                isTest ? "Test" : "Homework", date1.getTime(),
                new Date(date1.getTime() + (1000 * (60 * 60) * 12)).getTime()); //toString().substring(0, 10)
        AsyncTask.execute(() -> db.activityDao().insertActivity(task));
        createNotificationChannel();
        setNotificationForFutureTime(date1);
        notifyOfTheCreatedTask(task);
        Intent i = new Intent(AddTestActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        recreate();
    }

    private void notifyOfTheCreatedTask(StudentTask task) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "151")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("New Task Added")
                .setContentText(task.getSubjectName() + " " + task.getDescription())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(151, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = "DescriptionChannelForStudyCalendar";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channel_name), name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void setNotificationForFutureTime(Date date) {
        Intent i = new Intent(getApplicationContext(), AlarmBroadcastReceiver.class);
        PendingIntent pending = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarm.set(AlarmManager.RTC_WAKEUP, new Date(date.getTime() - (1000 * (60 * 60) * 12)).getTime(), pending);
    }


}