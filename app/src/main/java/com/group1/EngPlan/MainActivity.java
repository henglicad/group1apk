package com.group1.EngPlan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainActivitybtn2 = (Button) findViewById(R.id.MainActivitybtn2);
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
