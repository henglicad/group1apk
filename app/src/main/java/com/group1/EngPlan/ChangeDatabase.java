package com.group1.EngPlan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.MakeAPlan.MakeAPlanYearScreen;

public class ChangeDatabase extends AppCompatActivity {
    public static final String LOG_TAG = ChangeDatabase.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_db);

        Button logout = (Button) findViewById(R.id.button_logout);
        Button download = (Button) findViewById(R.id.button_download);
        Button upload = (Button) findViewById(R.id.button_upload);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainActivity);
            }
        });
    }
}
