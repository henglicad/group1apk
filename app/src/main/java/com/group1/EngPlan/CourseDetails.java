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
        courseName.setText(index + "\n");


        Cursor data = myDB.getCourseData(index);


        int columnCount = 1;
        data.moveToFirst();
        String temp = "not null";

        while(temp != null){
            temp = data.getString(columnCount);
            courseName.append(temp + "\n");
            columnCount++;
        }

    }
}
