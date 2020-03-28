package com.group1.EngPlan.MakeAPlan;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.group1.EngPlan.CentralActivity;
import com.group1.EngPlan.Backend.DatabaseHandler;
import com.group1.EngPlan.R;
import com.group1.EngPlan.Backend.ScheduleGenerator;

public class SemesterUpdate extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton RadioBtn;
    public String Semester;
    DatabaseHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myDB = new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester_update);

        Intent intent = getIntent();
        final boolean message = intent.getBooleanExtra("Check", false);
        final int year = intent.getIntExtra("Year", 0);
        Semester = intent.getStringExtra("Semester");
        radioGroup = findViewById(R.id.courseNoRadioGroup1);
        Button nextBtn = findViewById(R.id.courseNoNextBtn1);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                RadioBtn = findViewById(radioId);
                String radioBtnChk;

                if(radioId == -1)
                    radioBtnChk = null;
                else {
                    radioBtnChk = RadioBtn.getText().toString();
                    String arr[] = radioBtnChk.split(" ", 2);
                    radioBtnChk = arr[0];
                }

                if(radioBtnChk == null) {
                    Toast.makeText(getApplicationContext(), "Please Select One of the above options", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (radioBtnChk.equals("Fall")) {
                        Semester = "F";
                    }
                    else {
                        Semester = "W";
                    }

                    goToCentral(year, Semester);
                }
            }
        });
    }

    public void goToCentral(int year, String Semester){
        ScheduleGenerator sg = new ScheduleGenerator(myDB);
        int choice = myDB.numCourses;

        boolean fin = sg.main(choice, Semester, year);

        myDB.year = year;
        myDB.semester = Semester;
        myDB.updateSemesters();

        Intent intent = new Intent(getApplicationContext(), CentralActivity.class);
        intent.putExtra("Choice", choice);
        intent.putExtra("Year",year);
        intent.putExtra("Semester", Semester);
        startActivity(intent);
    }
}
