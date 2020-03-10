package com.group1.EngPlan.MakeAPlan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.CentralActivity;
import com.group1.EngPlan.DatabaseHandler;
import com.group1.EngPlan.R;
import com.group1.EngPlan.schedule_generator;

public class NumberOfCoursesScreen extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton RadioBtn;
    public int choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DatabaseHandler myDB = new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_courses_screen);

        Intent intent = getIntent();
        final boolean message = intent.getBooleanExtra("Check", false);
        final int year = intent.getIntExtra("Year", 0);
        final String Semester = intent.getStringExtra("Semester");
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
                    Toast.makeText(getApplicationContext(), "Please Select One of the above Options", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (Integer.parseInt(radioBtnChk) == 3)
                        choice = 3;
                    else if (Integer.parseInt(radioBtnChk) == 4)
                        choice = 4;
                    else if (Integer.parseInt(radioBtnChk) == 5)
                        choice = 5;
                    else if (Integer.parseInt(radioBtnChk) == 6)
                        choice = 6;
                    else //if(Integer.parseInt(radioBtnChk) == 7)
                        choice = 7;

                    if(message){
                        boolean fin = false;
                        schedule_generator sg = new schedule_generator(myDB);
                        fin = sg.main(choice, Semester, year);


                        Intent intent = new Intent(getApplicationContext(), CentralActivity.class);
                        startActivity(intent);
                    }
                    else {
                        goToPassFail(choice, year, Semester);
                    }
                }
            }
        });
    }

    public void goToPassFail(int choice, int year, String Semester){
        Intent intent = new Intent(getApplicationContext(), PassFailScreen.class);
        intent.putExtra("Year",year);
        intent.putExtra("Semester", Semester);
        intent.putExtra("Choice", choice);
        intent.putExtra("First Time", true);
        startActivity(intent);
    }
}
