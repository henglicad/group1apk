package com.group1.EngPlan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import static com.group1.EngPlan.DatabaseHandler.LOG_TAG;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    boolean firstTime;

    //SQLiteDatabase db = this.getWritableDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE);
        firstTime = sharedPreferences.getBoolean("firstTime", true);

        if(firstTime){

            Log.d(LOG_TAG, "In first time screen if condition");
            DatabaseHandler myDB = new DatabaseHandler(this);
            //myDB.getWritableDatabase();


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    firstTime = false;
                    editor.putBoolean("firstTime", firstTime);
                    editor.apply();

                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 5000);
        }

        else{
            Intent i = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(i);
            finish();
        }



    }
}
