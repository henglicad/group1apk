package com.group1.EngPlan.ManualScheduling;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.R;

public class CustomScheduleAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custum_schedule_add);
        TextView textview = (TextView) findViewById(R.id.customAddTextView);
        Intent intent = getIntent();
        String test = intent.getStringExtra("Semester");
        textview.setText(test);
    }
}
