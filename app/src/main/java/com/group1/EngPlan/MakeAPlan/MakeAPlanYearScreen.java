package com.group1.EngPlan.MakeAPlan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.R;

public class MakeAPlanYearScreen extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton RadioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_aplan_year_screen);

        radioGroup = findViewById(R.id.yearInfoRadioGrp);
        Button nextBtn = findViewById(R.id.yearNextBtn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                RadioBtn = findViewById(radioId);
                String radioBtnChk;

                if(radioId == -1)
                    radioBtnChk = null;
                else
                    radioBtnChk = RadioBtn.getText().toString();


                if(radioBtnChk == getString(R.string.yearRadioBtn)){
                    goToNumberofCourses();
                }
                else if(radioBtnChk == getString(R.string.yearRadioBtn2)){
                    goToPassFail();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please Select One of the above Options", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goToPassFail(){
        Intent showPassFailScreen = new Intent(getApplicationContext(), PassFailScreen.class);
        String message = "Pass Fail Screen";
        showPassFailScreen.putExtra("Check", message);
        startActivity(showPassFailScreen);
    }

    public void goToNumberofCourses(){
        Intent showNumberOfCoursesScreen = new Intent(getApplicationContext(), PassFailScreen.class);
        String message = "Number Of Courses Screen";
        showNumberOfCoursesScreen.putExtra("Check", message);
        startActivity(showNumberOfCoursesScreen);
    }


}
