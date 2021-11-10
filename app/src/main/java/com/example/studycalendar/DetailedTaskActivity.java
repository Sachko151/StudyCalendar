package com.example.studycalendar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studycalendar.database.StudentTaskDatabase;

import java.util.Date;

public class DetailedTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_task);
        StudentTaskDatabase db = StudentTaskDatabase.getInstance(this);
        TextView detailedName = findViewById(R.id.tvDetailedTaskName);
        TextView detailedDesc = findViewById(R.id.tvDetailedTaskDescription);
        TextView detailedType = findViewById(R.id.tvTaskType);
        TextView detailedDate = findViewById(R.id.tvDetaTaskDate);
        AsyncTask.execute(() ->
                setTheDetailsFromTheTaskToTheTextViews(detailedName, detailedDesc, detailedType, detailedDate,
                        db.activityDao().getActivityList().get(getIntent().getExtras()
                                .getInt("id")).getSubjectName(),
                        db.activityDao().getActivityList().get(getIntent().getExtras()
                                .getInt("id")).getDescription(),
                        db.activityDao().getActivityList().get(getIntent().getExtras()
                                .getInt("id")).getExerciseType(),
                        new Date(db.activityDao().getActivityList().get(getIntent().getExtras()
                                .getInt("id")).getDueDate()).toString().substring(0, 10)
                )
        );
        Button goBack = findViewById(R.id.btnDetailedTaskGoBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailedTaskActivity.this, MainActivity.class);
                startActivity(i);
                recreate();
            }
        });
    }

    private void setTheDetailsFromTheTaskToTheTextViews(TextView tv1, TextView tv2, TextView tv3,
                                                        TextView tv4, String s1, String s2,
                                                        String s3, String s4) {
        tv1.setText(s1);
        tv2.setText(s2);
        tv3.setText(s3);
        tv4.setText(s4);
    }

}