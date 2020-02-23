package com.group1.EngPlan.MakeAPlan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.Adapters.CourseChoiceAdapter;
import com.group1.EngPlan.DatabaseHandler;
import com.group1.EngPlan.R;

import java.util.ArrayList;

import static com.group1.EngPlan.DatabaseHandler.LOG_TAG;

public class PassFailScreen extends AppCompatActivity {

    ArrayList<String> courseCode = new ArrayList<>();
    ArrayList<String> courseName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHandler myDB = new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_fail_screen);

        Log.d(LOG_TAG, "On Pass Fail Screen");
        ListView listView = (ListView) findViewById(R.id.passFailListView);
        Intent intent = getIntent();
        boolean message = intent.getBooleanExtra("Check", false);

        String[] terms = {"F1", "W1", "F2", "W2", "F3", "W3", "F4", "W4", "F5", "W5"};
        int termNo = 0;

        while(termNo < terms.length) {

            Cursor data = myDB.fillQuickView(terms[termNo]);

            data.moveToFirst();
            boolean check = true;
            while (check) {
                courseCode.add(data.getString(0));
                courseName.add(data.getString(1));
                check = data.moveToNext();
            }

            termNo++;
        }

        /*QuickView qv = new QuickView();
        courseCode = qv.getIdealScheduleCode();
        courseName = qv.getIdealScheduleName();*/

        CourseChoiceAdapter adapter = new CourseChoiceAdapter(this, courseCode, courseName);
        listView.setAdapter(adapter);



    }




}
