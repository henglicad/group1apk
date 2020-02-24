package com.group1.EngPlan.MakeAPlan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.DatabaseHandler;
import com.group1.EngPlan.R;

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

                if(Integer.parseInt(radioBtnChk) == 3)
                    choice = 3;
                else if(Integer.parseInt(radioBtnChk) == 4)
                    choice = 4;
                else if(Integer.parseInt(radioBtnChk) == 5)
                    choice = 5;
                else if(Integer.parseInt(radioBtnChk) == 6)
                    choice = 6;
                else if(Integer.parseInt(radioBtnChk) == 7)
                    choice = 7;



                if(message){
                    MakeAPlanYearScreen MAPS = new MakeAPlanYearScreen();
                    String[] s = MAPS.getData();
                    int year = Integer.parseInt(s[0]);
                    ///////////////////////////////////////main(choice, s[1], year);
                }
                else {
                    goToPassFail(message);
                }

            }
        });
    }

    public void goToPassFail(boolean message){
        Intent showPassFailScreen = new Intent(getApplicationContext(), PassFailScreen.class);
        showPassFailScreen.putExtra("Check", message);
        startActivity(showPassFailScreen);
    }
}
