package com.group1.EngPlan.MakeAPlan;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.R;

public class MakeAPlanYearScreen extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton RadioBtn;
    AlertDialog alertDialog1;
    int year;
    String semester = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_aplan_year_screen);

        radioGroup = findViewById(R.id.yearInfoRadioGrp);
        Button nextBtn = findViewById(R.id.yearNextBtn);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                CreateAlertDialogWithRadioButtonGroup();
            }
        });

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
                    year = 1;
                    goToNumberofCoursesT();
                }
                else if(radioBtnChk == getString(R.string.yearRadioBtn2)){
                    year = 2;
                    goToNumberofCoursesF();
                }
                else if(radioBtnChk == getString(R.string.yearRadioBtn3)){
                    year = 3;
                    goToNumberofCoursesF();
                }
                else if(radioBtnChk == getString(R.string.yearRadioBtn4)){
                    year = 4;
                    goToNumberofCoursesF();
                }
                else if(radioBtnChk == getString(R.string.yearRadioBtn5)){
                    year = 5;
                    goToNumberofCoursesF();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please Select One of the above Options", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String[] getData(){
        String[] s = new String[2];
        s[0] = String.valueOf(year);
        s[1] = semester;

        return s;
    }


    public void goToNumberofCoursesT(){
        Intent showNumberOfCoursesScreen = new Intent(getApplicationContext(), NumberOfCoursesScreen.class);
        boolean message = true;
        showNumberOfCoursesScreen.putExtra("Check", message);
        showNumberOfCoursesScreen.putExtra("Year",year);
        showNumberOfCoursesScreen.putExtra("Semester", semester);
        startActivity(showNumberOfCoursesScreen);
    }

    public void goToNumberofCoursesF(){
        Intent showNumberOfCoursesScreen = new Intent(getApplicationContext(), NumberOfCoursesScreen.class);
        boolean message = false;
        showNumberOfCoursesScreen.putExtra("Check", message);
        showNumberOfCoursesScreen.putExtra("Year",year);
        showNumberOfCoursesScreen.putExtra("Semester", semester);
        startActivity(showNumberOfCoursesScreen);
    }

    public void CreateAlertDialogWithRadioButtonGroup(){

        String[] values = {"Fall Semester", "Winter Semester"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        builder.setTitle("Select Your Choice");

        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

            switch(item)
            {
                case 0:

                    Toast.makeText(getApplicationContext(), "Fall Semester Selected", Toast.LENGTH_LONG).show();
                    semester = "F";
                    break;

                case 1:

                    Toast.makeText(getApplicationContext(), "Winter Semester Selected", Toast.LENGTH_LONG).show();
                    semester = "W";
                    break;
            }

            alertDialog1.dismiss();
            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();

    }

}
