package com.group1.EngPlan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.Backend.DatabaseHandler;

public class CourseDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        DatabaseHandler myDB = new DatabaseHandler(this);

        Intent in = getIntent();
        String index = in.getStringExtra("com.group1.INDEX");
        TextView courseID = (TextView) findViewById(R.id.courseInfoTextView);
        TextView courseName = (TextView) findViewById(R.id.CourseName);
        TextView offered = (TextView) findViewById(R.id.Offering);
        TextView prereqTitle = (TextView) findViewById(R.id.PreReqListTitle);
        TextView toTitle = (TextView) findViewById(R.id.ToListTitle);
        TextView prereqList = (TextView) findViewById(R.id.PreReqList);
        TextView toList = (TextView) findViewById(R.id.ToList);

        prereqTitle.setVisibility(View.INVISIBLE);
        toTitle.setVisibility(View.INVISIBLE);
        prereqList.setVisibility(View.INVISIBLE);
        toList.setVisibility(View.INVISIBLE);

        Cursor data = myDB.getCourseData(index);

        data.moveToFirst();
        String temp = data.getString(0);
        courseID.setText(temp);
        temp = data.getString(1);
        courseName.setText(temp);


        temp = data.getString(2);
        String output = "This course is offered in Fall and Winter semesters";
        switch(temp){
            case "F":
                output = "This course is offered in Fall semester";
                break;
            case "W":
                output = "This course is offered in Winter semester";
                break;
            default:
                break;
        }
        offered.setText(output);

        temp = data.getString(3);
        if(temp != null){
            prereqTitle.setVisibility(View.VISIBLE);
            prereqTitle.setText("Prerequisites");
            prereqList.setVisibility(View.VISIBLE);

            prereqList.setText(temp + "\n");
            temp = data.getString(4);
            if(temp != null) {
                prereqList.append(temp + "\n");
            }
        }

        temp = data.getString(5);
        if(temp != null){
            toTitle.setVisibility(View.VISIBLE);
            toTitle.setText("Needed Before");
            toList.setVisibility(View.VISIBLE);

            toList.setText(temp + "\n");
            for(int i = 6; i <= 8; i++){
                temp = data.getString(i);
                if(temp != null) {
                    toList.append(temp + "\n");
                }
                else{
                    break;
                }
            }
        }
    }
}
