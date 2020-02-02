package com.group1.EngPlan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CourseDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        DatabaseHandler myDB = new DatabaseHandler(this);

        Intent in = getIntent();
        String index = in.getStringExtra("com.group1.INDEX");
        TextView courseName = (TextView) findViewById(R.id.courseInfoTextView);
        courseName.setText(index);

        Cursor data = myDB.getCourseData(index);
    }
}
