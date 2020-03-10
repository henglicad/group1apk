package com.group1.EngPlan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class ChangeDatabase extends AppCompatActivity {
    public static final String LOG_TAG = ChangeDatabase.class.getSimpleName();
    DatabaseHandler db;
    private static final String FILE_NAME = "downloadedDB.txt";

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
                saveToFile();
                Toast.makeText(getApplicationContext(), "Downloaded!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // disallow use of back button: require user to logout
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Use Logout button", Toast.LENGTH_SHORT).show();
    }

    // save data to file
    public void saveToFile(){
        FileOutputStream fos = null;

        try {
            Cursor courseData = db.sendDBData();
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);

            String data1 = "Current Database for TRU Engineering Program as of " + Calendar.getInstance().getTime() + "\n"
                    + "COURSE ID | COURSE NAME | OFFERED IN | IDEALLY | "
                    + "PRE REQ1 | PRE REQ2 | POST REQ |POST REQ |POST REQ |POST REQ | \n\n";
            fos.write(data1.getBytes());

            courseData.moveToFirst();
            String temp, line = "";
            int allocated[] = {8, 50, 1, 2, 8, 8, 8, 8, 8, 8};
            int numSpaces;

            do{
                for(int i = 0; i < 10; i++){
                    temp = courseData.getString(i);
                    line = line + temp;

                    if(temp != null) {
                        numSpaces = allocated[i] - temp.length();
                    }
                    else{
                        numSpaces = 4;
                    }
                        for (int j = 0; j < numSpaces; j++) {
                            line = line + " ";
                        }
                    line = line  + " | ";
                }

                line = line + "\n";
                fos.write(line.getBytes());
                line = "";
            }while(courseData.moveToNext());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
