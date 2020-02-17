package com.group1.EngPlan.MakeAPlan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.R;

import static com.group1.EngPlan.DatabaseHandler.LOG_TAG;

public class PassFailScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_fail_screen);

        Log.d(LOG_TAG, "On Pass Fail Screen");

        TextView textview = (TextView) findViewById(R.id.tempTextView);
        Intent intent = getIntent();
        String message = intent.getStringExtra("Check");
        textview.setText(message);
    }




}
