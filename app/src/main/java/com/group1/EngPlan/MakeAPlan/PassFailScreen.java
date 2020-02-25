package com.group1.EngPlan.MakeAPlan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.Adapters.CourseChoiceAdapter;
import com.group1.EngPlan.CentralActivity;
import com.group1.EngPlan.DatabaseHandler;
import com.group1.EngPlan.R;
import com.group1.EngPlan.schedule_generator;

import java.util.ArrayList;

import static com.group1.EngPlan.DatabaseHandler.LOG_TAG;

public class PassFailScreen extends AppCompatActivity {

    ArrayList<String> courseCode = new ArrayList<>();
    ArrayList<String> courseName = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseHandler myDB = new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_fail_screen);

        Log.d(LOG_TAG, "On Pass Fail Screen");
        ListView listView = (ListView) findViewById(R.id.passFailListView);
        Button passFailNext = (Button) findViewById(R.id.passFailNextBtn);
        Intent intent = getIntent();

        final int year = intent.getIntExtra("Year", 0);
        final String Semester = intent.getStringExtra("Semester");
        final int choice = intent.getIntExtra("Choice", 0);



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


        final CourseChoiceAdapter adapter = new CourseChoiceAdapter(this, courseCode, courseName);
        listView.setAdapter(adapter);


        passFailNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean[] chk = adapter.checkBoxState;

                        for(int i = 0; i < chk.length; i++){
                            if(chk[i] == true)
                                myDB.setRecords(courseCode.get(i), 1);
                            else
                                myDB.setRecords(courseCode.get(i), 0);
                        }

                        boolean fin = false;
                        schedule_generator sg = new schedule_generator(myDB);
                        fin = sg.main(choice, Semester, year);

                        Intent intent = new Intent(getApplicationContext(), CentralActivity.class);
                        startActivity(intent);

                    }
        });






    }




}
