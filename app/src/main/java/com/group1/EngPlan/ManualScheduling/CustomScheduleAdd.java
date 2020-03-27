package com.group1.EngPlan.ManualScheduling;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.Adapters.CourseAdapter;
import com.group1.EngPlan.Backend.DatabaseHandler;
import com.group1.EngPlan.R;

import java.util.ArrayList;

public class CustomScheduleAdd extends AppCompatActivity {

    private ArrayList<String> courseName = new ArrayList<>();
    private ArrayList<String> courseCode = new ArrayList<>();
    private ArrayList<String> courseCodeTest = new ArrayList<>();
    private final ArrayList<String> reqs = new ArrayList<>();
    private String semester, test;
    private int check, semesterPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custum_schedule_add);
        TextView textview = (TextView) findViewById(R.id.customAddTextView);
        ListView listView = (ListView) findViewById(R.id.customAddListView);

        Intent intent = getIntent();
        courseCode = intent.getStringArrayListExtra("Course Code");
        courseCodeTest = (ArrayList<String>) courseCode.clone();
        courseName = intent.getStringArrayListExtra("Course Name");
        test = intent.getStringExtra("Semester");
        semesterPosition = intent.getIntExtra("Position", 0);

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
                while(check == 1 ){
                    courseName.remove(i);
                    courseCode.remove(i);
                    if(i < courseCode.size()){
                        check = getItemViewType(i);
                    }
                    else {
                        break;
                    }
                }
                break;
            }
        }

        displayList(listView);
    }

    private void displayList(ListView listView){
        CourseAdapter courseAdapter = new CourseAdapter(this, courseCode, courseName);
        final DatabaseHandler myDB = new DatabaseHandler(this);

        listView.setAdapter(courseAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(getItemViewType(position) == 1){
                    boolean courseCheck, preReqCheck = false, preReqComplete = false, postReqComplete = false, reqComplete;
                    int preReqNo = -1, preReqMatch = -1, postReqNo = 0, postReqMatch = 0;
                    String course = courseCode.get(position);
                    courseCheck = myDB.checkSemester(course, String.valueOf(test.charAt(0)));

                    Cursor data = myDB.getCourseData(course);
                    data.moveToFirst();
                    reqs.add(data.getString(3));
                    reqs.add(data.getString(4));
                    reqs.add(data.getString(5));
                    reqs.add(data.getString(6));
                    reqs.add(data.getString(7));
                    reqs.add(data.getString(8));

                    for(int i = 0; i <= 1; i++){
                        if(reqs.get(i) != null){
                            preReqNo++;
                        }
                    }
                    for(int i = 2; i < reqs.size(); i++){
                        if(reqs.get(i) != null){
                            postReqNo++;
                        }
                    }

                    int i = 0;
                    while(!courseCodeTest.get(i).equals(test)){
                        int count = preReqNo;
                        while(count >= 0){
                            if(courseCodeTest.get(i).equals(reqs.get(count))){
                                preReqCheck = true;
                            }
                            count--;
                        }
                        if(preReqCheck){
                            preReqMatch++;
                            preReqCheck = false;
                        }
                        if(preReqMatch == preReqNo){
                            preReqComplete = true;
                            break;
                        }
                        i++;
                    }

                   for(int j = semesterPosition; j < courseCodeTest.size(); j++){
                       for(int k = 2; k < postReqNo; k++){
                           if(courseCodeTest.get(j).equals(reqs.get(k))){
                               postReqMatch++;
                           }
                       }
                       if(postReqMatch == postReqNo){
                           postReqComplete = true;
                           break;
                       }
                   }

                   reqComplete = preReqComplete & postReqComplete;

                    if(course.contains("COOP2")) {
                        if(reqComplete){
                            if (semesterPosition + 1 != courseName.size()) {
                                if (getItemViewType(semesterPosition + 1) == 1) {
                                    alertDialog1("CO");
                                } else {
                                    alertDialogAreYouSure(myDB, position);
                                }
                            }
                        }
                        else{
                            if (semesterPosition + 1 != courseName.size()) {
                                if (getItemViewType(semesterPosition + 1) == 1) {
                                    alertDialog1("CO");
                                } else {
                                    alertDialogueMissingPreReq(myDB, position, preReqComplete, postReqComplete);
                                }
                            }
                        }

                    }
                    else if(courseCheck){
                        if(reqComplete){
                            alertDialogAreYouSure(myDB, position);
                        }
                        else{
                            alertDialogueMissingPreReq(myDB, position, preReqComplete, postReqComplete);
                        }
                    }
                    else{
                        alertDialog1(test);
                    }
                reqs.clear();
                }
            }
        });

    }

    private void alertDialogAreYouSure(final DatabaseHandler myDB,final int position){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Hey!");
        dialog.setMessage("Are you sure you want to alter your schedule? This change is irreversible.");
        dialog.setPositiveButton("Yes Of Course",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        myDB.manualChange(courseCode.get(position), test);
                        reqs.clear();
                        Intent intent = new Intent(getApplicationContext(), CustomScheduleEdit.class);
                        startActivity(intent);

                    }
                });
        dialog.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        reqs.clear();
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();

    }

    private void alertDialogueMissingPreReq(final DatabaseHandler myDB,final int position, boolean preReqComplete, boolean postReqComplete){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        String alertMessage = "";

        for(int i = 0; i < reqs.size(); i++){
            if((i <= 1) && (preReqComplete == false)){
                if(i == 0){
                    alertMessage = alertMessage + "\nPre-Reqs:\n";
                }
                if(reqs.get(i) != null){
                    alertMessage = "    " + alertMessage + reqs.get(i) + "\n";
                }

            }
            else if(postReqComplete == false){
                if(i == 2){
                    alertMessage = alertMessage + "\nPost-Reqs:\n";
                }
                if(reqs.get(i) != null){
                    alertMessage = "    " + alertMessage + reqs.get(i) + "\n";
                }
            }
        }

        dialog.setTitle("Hey!");
        dialog.setMessage("\n Moving this course will result in an invalid schedule.\n" + alertMessage +
                "\nAre you sure you want to alter your schedule? This change is irreversible.");
        dialog.setPositiveButton("Yes Of Course",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        myDB.manualChange(courseCode.get(position), test);
                        reqs.clear();
                        Intent intent = new Intent(getApplicationContext(), CustomScheduleEdit.class);
                        startActivity(intent);
                    }
                });
        dialog.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        reqs.clear();
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();

    }

    private void alertDialog1(String test) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        if(test.charAt(0) == 'F'){
            dialog.setMessage("The course you have selected is not offered in the Fall Semester.");
        }
        else if(test.charAt(0) == 'W'){
            dialog.setMessage("The course you have selected is not offered in the Winter Semester.");
        }
        else{
            dialog.setMessage("COOP2XXX series courses cannot be moved to non empty semesters because they are work terms.");
        }
        dialog.setTitle("Hey!");
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        reqs.clear();
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
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
