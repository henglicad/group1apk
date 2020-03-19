package com.group1.EngPlan.ManualScheduling;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.Adapters.CourseManualAdapter;
import com.group1.EngPlan.CourseDetails;
import com.group1.EngPlan.DatabaseHandler;
import com.group1.EngPlan.R;

import java.util.ArrayList;

public class CustomScheduleEdit extends AppCompatActivity {

    ArrayList<String> courseCode = new ArrayList<>();
    ArrayList<String> courseName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_schedule_edit);

        displayList();
    }

    public void displayList(){

        final DatabaseHandler myDB = new DatabaseHandler(this);
        ListView listView = (ListView) findViewById(R.id.custom_schedule_list_view);

        String[] terms = {"F1", "W1", "F2", "W2", "F3", "W3", "F4", "W4", "F5", "W5", "F6", "W6", "F7", "W7"};
        int termNo = 0;
        while(termNo < terms.length) {

            Cursor data = myDB.getSavedSched(terms[termNo]);
            courseName.add(terms[termNo]);
            courseCode.add(terms[termNo]);

            data.moveToFirst();
            //String s;

            data.moveToFirst();
            boolean check = true;
            if(data.getCount() != 0){
                while (check) {
                    //s = DatabaseUtils.dumpCurrentRowToString(data);
                    courseCode.add(data.getString(0));
                    courseName.add(data.getString(1));
                    check = data.moveToNext();
                }
            }

            termNo++;
        }

        for(int i = 0; i < courseCode.size()-1; i++){
            if((getItemViewType(i) == 0) && (getItemViewType(i+1) == 0)){
                courseName.remove(i);
                courseCode.remove(i);
                i--;
            }
        }
        int temp = courseCode.size();
        if(getItemViewType(temp-1) == 0){
            courseName.remove(temp-1);
            courseCode.remove(temp-1);
        }


        CourseManualAdapter courseManualAdapter = new CourseManualAdapter(this, courseCode, courseName);
        listView.setAdapter(courseManualAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = courseCode.get(position);
                if(getItemViewType(position) != 0){
                    Intent showCourseInfo = new Intent(getApplicationContext(), CourseDetails.class);
                    showCourseInfo.putExtra("com.group1.INDEX", data);
                    startActivity(showCourseInfo);
                }

            }
        });
    }

    public int getItemViewType(int position){
        if((courseName.get(position) == "F1") || (courseName.get(position) == "W1") ||(courseName.get(position) == "F2") || (courseName.get(position) == "W2") ||
                (courseName.get(position) == "F3") || (courseName.get(position) == "W3") || (courseName.get(position) == "F4") || (courseName.get(position) == "W4") ||
                (courseName.get(position) == "F5") || (courseName.get(position) == "W5")||(courseName.get(position) == "F6") || (courseName.get(position) == "W6") ||
                (courseName.get(position) == "F7") || (courseName.get(position) == "W7") || (courseName.get(position) == "F8") || (courseName.get(position) == "W8")||
                (courseName.get(position) == "F9") || (courseName.get(position) == "W9")|| (courseName.get(position) == "F10") || (courseName.get(position) == "W10")||
                (courseName.get(position) == "F11") || (courseName.get(position) == "W11")){
            return 0;}
        return 1;
    }
}
