package com.group1.EngPlan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuickView extends AppCompatActivity {

    ArrayList<String> idealScheduleCode = new ArrayList<>();
    ArrayList<String> idealScheduleName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHandler myDB = new DatabaseHandler(this);

        schedule_generator sg = new schedule_generator(myDB);
        sg.test();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_view);

        ListView listView = (ListView) findViewById(R.id.ListViewIdeal);
        String[] terms = {"F1", "W1", "F2", "W2", "F3", "W3", "F4", "W4", "F5", "W5"};
        int termNo = 0;

        ArrayList<String> Semester = new ArrayList<>();


        while(termNo < terms.length) {

            Cursor data = myDB.fillQuickView(terms[termNo]);
            idealScheduleName.add(terms[termNo]);
            idealScheduleCode.add(terms[termNo]);

            data.moveToFirst();
            boolean check = true;
            while (check) {
                idealScheduleCode.add(data.getString(0));
                check = data.moveToNext();
            }

            data.moveToFirst();
            check = true;
            while (check) {
                idealScheduleName.add(data.getString(1));
                check = data.moveToNext();
            }

            termNo++;
        }

        CourseAdapter courseAdapter = new CourseAdapter(this, idealScheduleCode, idealScheduleName);
        listView.setAdapter(courseAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = idealScheduleCode.get(position);
                if((idealScheduleName.get(position) == "F1") || (idealScheduleName.get(position) == "W1") ||(idealScheduleName.get(position) == "F2") || (idealScheduleName.get(position) == "W2") ||
                        (idealScheduleName.get(position) == "F3") || (idealScheduleName.get(position) == "W3") || (idealScheduleName.get(position) == "F4") || (idealScheduleName.get(position) == "W4") ||
                        (idealScheduleName.get(position) == "F5") || (idealScheduleName.get(position) == "W5")){


                }
                 else {
                    Intent showCourseInfo = new Intent(getApplicationContext(), CourseDetails.class);
                    showCourseInfo.putExtra("com.group1.INDEX", data);
                    startActivity(showCourseInfo);
                }
            }
        });
    }
}
