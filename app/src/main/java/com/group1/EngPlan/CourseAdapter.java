package com.group1.EngPlan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    ArrayList<String> idealScheduleCode;
    ArrayList<String> idealScheduleName;
    String st;


    public CourseAdapter(Context c, ArrayList<String> i, ArrayList<String> j){
        idealScheduleCode = i;
        idealScheduleName = j;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public CourseAdapter(Context c, String s){
        st = s;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return idealScheduleCode.size();
    }

    @Override
    public Object getItem(int position) {
        return idealScheduleCode.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String temp = idealScheduleName.get(position);

        if(getItemViewType(position) == 1) {
            View v = mInflater.inflate(R.layout.listview_layout_quick_view, null);
            TextView courseCode = (TextView) v.findViewById(R.id.textViewCourseCode);
            TextView courseName = (TextView) v.findViewById(R.id.TextView2);

            String code =  idealScheduleCode.get(position);
            String name =  idealScheduleName.get(position);

            courseCode.setText(code);
            courseName.setText(name);
            return v;
        }

        else if(getItemViewType(position) == 0){
            View v = mInflater.inflate(R.layout.listview_headings_quick_view, null);
            TextView courseSem = (TextView) v.findViewById(R.id.TextViewSemester);

            String semester = idealScheduleName.get(position);
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
        if((idealScheduleName.get(position) == "F1") || (idealScheduleName.get(position) == "W1") ||(idealScheduleName.get(position) == "F2") || (idealScheduleName.get(position) == "W2") ||
                (idealScheduleName.get(position) == "F3") || (idealScheduleName.get(position) == "W3") || (idealScheduleName.get(position) == "F4") || (idealScheduleName.get(position) == "W4") ||
                (idealScheduleName.get(position) == "F5") || (idealScheduleName.get(position) == "W5")){
        return 0;}
        return 1;
    }

}
