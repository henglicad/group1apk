package com.group1.EngPlan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.MakeAPlan.MakeAPlanYearScreen;

public class MainActivity extends AppCompatActivity {

    //DatabaseHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainActivitybtn1 = (Button) findViewById(R.id.MainActivitybtn1);
        Button mainActivitybtn2 = (Button) findViewById(R.id.MainActivitybtn2);

        mainActivitybtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent makeAPlan = new Intent(getApplicationContext(), MakeAPlanYearScreen.class);
                startActivity(makeAPlan);
            }
        });

        mainActivitybtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quickView = new Intent(getApplicationContext(), QuickView.class);
                startActivity(quickView);
            }
        });

        //@Override
        //public void onClick(View v){
          //  Intent quickView = new Intent(this, QuickView.class);
            //startActivity(quickView);
        //}

    }
}
