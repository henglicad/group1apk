package com.group1.EngPlan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.Backend.DatabaseHandler;

public class CourseDetails extends AppCompatActivity {

    Cursor data;

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
        TextView prereqList2 = (TextView) findViewById(R.id.PreReqList2);
        TextView toList = (TextView) findViewById(R.id.ToList);
        TextView toList2 = (TextView) findViewById(R.id.ToList2);
        TextView toList3 = (TextView) findViewById(R.id.ToList3);
        TextView toList4 = (TextView) findViewById(R.id.ToList4);

        prereqTitle.setVisibility(View.INVISIBLE);
        toTitle.setVisibility(View.INVISIBLE);
        prereqList.setVisibility(View.INVISIBLE);
        prereqList2.setVisibility(View.INVISIBLE);
        toList.setVisibility(View.INVISIBLE);
        toList2.setVisibility(View.INVISIBLE);
        toList3.setVisibility(View.INVISIBLE);
        toList4.setVisibility(View.INVISIBLE);

        data = myDB.getCourseData(index);

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
            prereqList.setText(temp);

            temp = data.getString(4);
            if(temp != null) {
                prereqList2.setVisibility(View.VISIBLE);
                prereqList2.append(temp);
            }
        }

        temp = data.getString(5);
        if(temp != null){
            toTitle.setVisibility(View.VISIBLE);
            toTitle.setText("Postrequisites");
            toList.setVisibility(View.VISIBLE);
            toList.setText(temp);

            temp = data.getString(6);
            if(temp != null){
                toList2.setVisibility(View.VISIBLE);
                toList2.setText(temp);

                temp = data.getString(7);
                if(temp != null){
                    toList3.setVisibility(View.VISIBLE);
                    toList3.setText(temp);

                    temp = data.getString(8);
                    if(temp != null){
                        toList4.setVisibility(View.VISIBLE);
                        toList4.setText(temp);
                    }
                }
            }
        }
    }

    public void goToPre1(View view) {
        String course = data.getString(3);
        finish();
        Intent showCourseInfo = new Intent(getApplicationContext(), CourseDetails.class);
        showCourseInfo.putExtra("com.group1.INDEX", course);
        startActivity(showCourseInfo);
    }

    public void goToPre2(View view) {
        String course = data.getString(4);
        finish();
        Intent showCourseInfo = new Intent(getApplicationContext(), CourseDetails.class);
        showCourseInfo.putExtra("com.group1.INDEX", course);
        startActivity(showCourseInfo);
    }

    public void goToPost1(View view) {
        String course = data.getString(5);
        finish();
        Intent showCourseInfo = new Intent(getApplicationContext(), CourseDetails.class);
        showCourseInfo.putExtra("com.group1.INDEX", course);
        startActivity(showCourseInfo);
    }

    public void goToPost2(View view) {
        String course = data.getString(6);
        finish();
        Intent showCourseInfo = new Intent(getApplicationContext(), CourseDetails.class);
        showCourseInfo.putExtra("com.group1.INDEX", course);
        startActivity(showCourseInfo);
    }

    public void goToPost3(View view) {
        String course = data.getString(7);
        finish();
        Intent showCourseInfo = new Intent(getApplicationContext(), CourseDetails.class);
        showCourseInfo.putExtra("com.group1.INDEX", course);
        startActivity(showCourseInfo);
    }

    public void goToPost4(View view) {
        String course = data.getString(8);
        finish();
        Intent showCourseInfo = new Intent(getApplicationContext(), CourseDetails.class);
        showCourseInfo.putExtra("com.group1.INDEX", course);
        startActivity(showCourseInfo);
    }
}
