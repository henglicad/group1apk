package com.group1.EngPlan.ManualScheduling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.Adapters.CourseAdapter;
import com.group1.EngPlan.CourseDetails;
import com.group1.EngPlan.R;

import java.util.ArrayList;

public class CustomScheduleAdd extends AppCompatActivity {

    ArrayList<String> courseName = new ArrayList<>();
    ArrayList<String> courseCode = new ArrayList<>();
    String semester;
    int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custum_schedule_add);
        TextView textview = (TextView) findViewById(R.id.customAddTextView);
        ListView listView = (ListView) findViewById(R.id.customAddListView);


        Intent intent = getIntent();
        courseCode = intent.getStringArrayListExtra("Course Code");
        courseName = intent.getStringArrayListExtra("Course Name");
        String test = intent.getStringExtra("Semester");

        switch(test){
            case "F1":
                semester = "Fall 1";
                break;
            case "W1":
                semester = "Winter 1";
                break;
            case "F2":
                semester = "Fall 2";
                break;
            case "W2":
                semester = "Winter 2";
                break;
            case "F3":
                semester = "Fall 3";
                break;
            case "W3":
                semester = "Winter 3";
                break;
            case "F4":
                semester = "Fall 4";
                break;
            case "W4":
                semester = "Winter 4";
                break;
            case "F5":
                semester = "Fall 5";
                break;
            case "W5":
                semester = "Winter 5";
                break;
            case "F6":
                semester = "Fall 6";
                break;
            case "W6":
                semester = "Winter 6";
                break;
            case "F7":
                semester = "Fall 7";
                break;
            case "W7":
                semester = "Winter 7";
                break;
            case "F8":
                semester = "Fall 8";
                break;
            case "W8":
                semester = "Winter 8";
                break;
            case "F9":
                semester = "Fall 9";
                break;
            case "W9":
                semester = "Winter 9";
                break;
            case "F10":
                semester = "Fall 10";
                break;
            case "W10":
                semester = "Winter 10";
                break;
            case "F11":
                semester = "Fall 11";
                break;
            case "W11":
                semester = "Winter 11";
                break;
        }

        textview.append(" " + semester);

        for(int i = 0; i < courseCode.size(); i++){
            if(courseName.get(i).equals(test)){
                courseName.remove(i);
                courseCode.remove(i);
                check = getItemViewType(i);
                while(check == 1){
                    courseName.remove(i);
                    courseCode.remove(i);
                    check = getItemViewType(i);
                }
                break;
            }
        }

        displayList(listView);
    }

    private void displayList(ListView listView){
        CourseAdapter courseAdapter = new CourseAdapter(this, courseCode, courseName);
        listView.setAdapter(courseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(getItemViewType(position) == 1){
                    Intent showCourseInfo = new Intent(getApplicationContext(), CourseDetails.class);
                    //showCourseInfo.putExtra("com.group1.INDEX", data);
                    startActivity(showCourseInfo);

                }

            }
        });
    }

    public int getItemViewType(int position){
        if((courseName.get(position).equals("F1")) || (courseName.get(position).equals("W1")) ||(courseName.get(position).equals("F2")) || (courseName.get(position).equals("W2")) ||
                (courseName.get(position).equals("F3")) || (courseName.get(position).equals("W3")) || (courseName.get(position).equals("F4")) || (courseName.get(position).equals("W4")) ||
                (courseName.get(position).equals("F5")) || (courseName.get(position).equals("W5")) || (courseName.get(position).equals("F6")) || (courseName.get(position).equals("W6")) ||
                (courseName.get(position).equals("F7")) || (courseName.get(position).equals("W7")) || (courseName.get(position).equals("F8")) || (courseName.get(position).equals("W8"))||
                (courseName.get(position).equals("F9")) || (courseName.get(position).equals("W9")) || (courseName.get(position).equals("F10")) || (courseName.get(position).equals("W10"))||
                (courseName.get(position).equals("F11")) || (courseName.get(position).equals("W11"))){
            return 0;}
        return 1;
    }
}
