package com.group1.EngPlan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.MakeAPlan.MakeAPlanYearScreen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class ChangeDatabase extends AppCompatActivity {
    public static final String LOG_TAG = ChangeDatabase.class.getSimpleName();
    DatabaseHandler db;
    final static String path = "/sdcard/myApp"; //Environment.getExternalStorageDirectory().getAbsolutePath() + "/instinctcoder/readwrite/" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_db);
        db = new DatabaseHandler(this);

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

        download.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Cursor courseData = db.sendDBData();
                saveToFile("hello");
                Toast.makeText(getApplicationContext(), "Downloaded!", Toast.LENGTH_SHORT).show();
                //write code to navigate through cursor and send to file
            }
        });
    }

    // disallow use of back button: require user to logout
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Use Logout button", Toast.LENGTH_SHORT).show();
    }

    // save data to file
    public static boolean saveToFile( String data){
        String fileName = "database_" + Calendar.getInstance().getTime() + ".txt";

        try {
            new File(path).mkdirs();
            File file = new File(path+ fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());

            return true;
        }  catch(FileNotFoundException ex) {
            Log.d(LOG_TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(LOG_TAG, ex.getMessage());
        }
        return  false;
    }
}
