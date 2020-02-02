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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_view);

        ListView listView = (ListView) findViewById(R.id.ListViewIdeal);
        String[] terms = {"F1", "W1", "F2", "W2", "F3", "W3", "F4", "W4"};
        int termNo = 0;

        ArrayList<String> Semester = new ArrayList<>();


        while(termNo < 8) {
            //myDB = new DatabaseHandler(this);
            //Log.d(LOG_TAG, "Before method call");


            Cursor data = myDB.fillQuickView(terms[termNo]);
            //Log.d(LOG_TAG, "Didn't crash yet");
            //if(data.getCount == 0){
            //  Toast.makeText(QuickView.this, "The Database Table is Empty. ",Toast.LENGTH_LONG).show();
            //}
            //else{
            data.moveToFirst();
            while (data.moveToNext()) {
                idealScheduleCode.add(data.getString(0));
            }
            data.moveToFirst();
            while (data.moveToNext()) {
                idealScheduleName.add(data.getString(1));
            }

            CourseAdapter courseAdapter = new CourseAdapter(this, idealScheduleCode, idealScheduleName);
            listView.setAdapter(courseAdapter);

            //}
            termNo++;


        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = idealScheduleCode.get(position);
                Intent showCourseInfo = new Intent(getApplicationContext(), CourseDetails.class);
                showCourseInfo.putExtra("com.group1.INDEX", data);
                startActivity(showCourseInfo);
            }
        });
    }
}
