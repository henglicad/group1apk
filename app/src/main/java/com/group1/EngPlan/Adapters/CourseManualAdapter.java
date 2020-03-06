package com.group1.EngPlan.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.group1.EngPlan.R;

import java.util.ArrayList;

public class CourseManualAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<String> courseCode;
    private ArrayList<String> courseName;



    public CourseManualAdapter(Context c, ArrayList<String> i, ArrayList<String> j){
        courseCode = i;
        courseName = j;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return courseCode.size();
    }

    @Override
    public Object getItem(int position) {
        return courseCode.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String temp = courseName.get(position);

        if(getItemViewType(position) == 1) {
            View v = mInflater.inflate(R.layout.listview_layout_quick_view, null);
            TextView courseCodeText = (TextView) v.findViewById(R.id.textViewCourseCode);
            TextView courseNameText = (TextView) v.findViewById(R.id.TextView2);

            String code =  courseCode.get(position);
            String name =  courseName.get(position);

            courseCodeText.setText(code);
            courseNameText.setText(name);
            return v;
        }

        else if(getItemViewType(position) == 0){
            View v = mInflater.inflate(R.layout.listview_headings_custom_schedule, null);
            TextView courseSem = (TextView) v.findViewById(R.id.TextViewSemesterWBtn);
            Button plusBtn = (Button) v.findViewById(R.id.plusButton);
            Button editBtn = (Button) v.findViewById(R.id.editButton);

            plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            String semester = courseName.get(position);
            String display = " ";
            switch(semester){
                case "F1":
                    display = "Fall 1";
                    break;
                case "W1":
                    display = "Winter 1";
                    break;
                case "F2":
                    display = "Fall 2";
                    break;
                case "W2":
                    display = "Winter 2";
                    break;
                case "F3":
                    display = "Fall 3";
                    break;
                case "W3":
                    display = "Winter 3";
                    break;
                case "F4":
                    display = "Fall 4";
                    break;
                case "W4":
                    display = "Winter 4";
                    break;
                case "F5":
                    display = "Fall 5";
                    break;
                case "W5":
                    display = "Winter 5";
                    break;
                case "F6":
                    display = "Fall 6";
                    break;
                case "W6":
                    display = "Winter 6";
                    break;
                case "F7":
                    display = "Fall 7";
                    break;
                case "W7":
                    display = "Winter 7";
                    break;
            }

            courseSem.setText(display);
            return v;
        }


        return null;

    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }

    @Override
    public int getItemViewType(int position){
        if((courseName.get(position) == "F1") || (courseName.get(position) == "W1") ||(courseName.get(position) == "F2") || (courseName.get(position) == "W2") ||
                (courseName.get(position) == "F3") || (courseName.get(position) == "W3") || (courseName.get(position) == "F4") || (courseName.get(position) == "W4") ||
                (courseName.get(position) == "F5") || (courseName.get(position) == "W5")||(courseName.get(position) == "F6") || (courseName.get(position) == "W6") ||
                (courseName.get(position) == "F7") || (courseName.get(position) == "W7")){
            return 0;}
        return 1;
    }

}