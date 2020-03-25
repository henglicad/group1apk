package com.group1.EngPlan.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.group1.EngPlan.ManualScheduling.CustomScheduleAdd;
import com.group1.EngPlan.ManualScheduling.CustomScheduleEdit;
import com.group1.EngPlan.R;

import java.util.ArrayList;

public class CourseManualAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<String> courseCode;
    private ArrayList<String> courseName;
    private int clickPosition;
    protected Context context;



    public CourseManualAdapter(Context c, ArrayList<String> i, ArrayList<String> j){
        courseCode = i;
        courseName = j;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = c;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

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
            //Button editBtn = (Button) v.findViewById(R.id.editButton);

            plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickPosition = position;
                    Intent intent = new Intent (context, CustomScheduleAdd.class);
                    if(courseName.get(position + 1).contains("COOP2")){
                        alertDialogue(position);
                    }
                    else{
                        intent.putExtra("Semester", courseName.get(position));
                        intent.putExtra("Position", position);
                        intent.putStringArrayListExtra("Course Name", courseName);
                        intent.putStringArrayListExtra("Course Code", courseCode);
                        context.startActivity(intent);
                    }
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
        if((courseName.get(position).equals("F1")) || (courseName.get(position).equals("W1")) ||(courseName.get(position).equals("F2")) || (courseName.get(position).equals("W2")) ||
                (courseName.get(position).equals("F3")) || (courseName.get(position).equals("W3")) || (courseName.get(position).equals("F4")) || (courseName.get(position).equals("W4")) ||
                (courseName.get(position).equals("F5")) || (courseName.get(position).equals("W5")) || (courseName.get(position).equals("F6")) || (courseName.get(position).equals("W6")) ||
                (courseName.get(position).equals("F7")) || (courseName.get(position).equals("W7")) || (courseName.get(position).equals("F8")) || (courseName.get(position).equals("W8"))||
                (courseName.get(position).equals("F9")) || (courseName.get(position).equals("W9")) || (courseName.get(position).equals("F10")) || (courseName.get(position).equals("W10"))||
                (courseName.get(position).equals("F11")) || (courseName.get(position).equals("W11"))){
            return 0;}
        return 1;
    }

    private void alertDialogue(final int position){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Hey!");
        dialog.setMessage("You cannot add a course to a work term, Are you sure you want to do this ?");
        dialog.setPositiveButton("Yes Of Course",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Intent intent = new Intent(context, CustomScheduleEdit.class);
                        intent.putExtra("Semester", courseName.get(position));
                        intent.putExtra("Position", position);
                        intent.putStringArrayListExtra("Course Name", courseName);
                        intent.putStringArrayListExtra("Course Code", courseCode);
                        context.startActivity(intent);
                    }
                });
        dialog.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

}