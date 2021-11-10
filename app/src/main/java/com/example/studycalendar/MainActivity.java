package com.example.studycalendar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studycalendar.database.StudentTaskDatabase;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button testButton = findViewById(R.id.btnAddTest);
        final Button resetButton = findViewById(R.id.btnReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddTestActivity.class);
                startActivity(i);

            }
        });
        StudentTaskDatabase db = StudentTaskDatabase.getInstance(this);

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> type = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();
        populateLists(db, names, type, dates);
        initTheRecyclerView(names, type, dates);
    }

    public void populateLists(StudentTaskDatabase db, ArrayList<String> names,
                              ArrayList<String> type, ArrayList<String> dates) {

        AsyncTask.execute(() ->
                {
                    for (int i = 0; i < db.activityDao().getActivityList().size(); i++) {
                        names.add(db.activityDao().getActivityList().get(i).getSubjectName());
                        type.add(db.activityDao().getActivityList().get(i).getExerciseType());
                        dates.add(new Date(db.activityDao().getActivityList().get(i)
                                .getDueDate()).toString().substring(0, 10));
                    }
                }

        );
    }

    public void initTheRecyclerView(ArrayList<String> names,
                                    ArrayList<String> type, ArrayList<String> dates) {
        RecyclerView rv = findViewById(R.id.rvUpcomingTasks);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RecycleViewAdapter adapter = new RecycleViewAdapter(this, names, type, dates);
        rv.setAdapter(adapter);
    }
}