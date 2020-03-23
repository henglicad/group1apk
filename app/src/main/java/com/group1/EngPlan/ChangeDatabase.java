package com.group1.EngPlan;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.Backend.DatabaseHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeDatabase extends AppCompatActivity {
    public static String LOG_TAG = ChangeDatabase.class.getSimpleName();
    DatabaseHandler db;

    private static String D_FILE_NAME;
    private static String U_FILE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_db);
        db = new DatabaseHandler(this);

        D_FILE_NAME = Environment.getExternalStorageDirectory()  + "/Download/downloadedDB.txt";
        U_FILE_NAME = Environment.getExternalStorageDirectory()  + "/Download/uploadedDB.txt";

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
            }
        });

        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(ChangeDatabase.this);

                builder.setTitle("Confirm Upload of 'uploadedDB.txt'");
                builder.setMessage("This will cause the schedule to be rebuilt.");

                builder.setPositiveButton("Yes, upload", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        writeToFile();
                        // Do nothing, but close the dialog
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("No, Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        LOG_TAG = null;
        D_FILE_NAME = null;
        U_FILE_NAME = null;

    }

    // disallow use of back button: require user to logout
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Use Logout button", Toast.LENGTH_SHORT).show();
    }

    // save data to file
    public void saveToFile(){
        File file = new File(D_FILE_NAME);
        Cursor courseData = db.sendDBData();

        if (file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            Toast.makeText(this, "Please allow permissions.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        FileWriter textFileWriter;
        try {
            textFileWriter = new FileWriter(file, false);

            BufferedWriter out = new BufferedWriter(textFileWriter);

            String data1 = "Current Database for TRU Engineering Program as of " + Calendar.getInstance().getTime() + "\n"
                    + "COURSE ID | COURSE NAME | OFFERED IN | IDEALLY | "
                    + "PRE REQ1 | PRE REQ2 | POST REQ |POST REQ |POST REQ |POST REQ | \n\n";

            try {
                out.write(data1);
            } catch (IOException e) {
                Toast.makeText(this, "Could not download", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            // write the updated content
            try {
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
                    out.write(line);
                    line = "";
                }while(courseData.moveToNext());
            } catch (IOException e) {
                Toast.makeText(this, "Could not download", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                Toast.makeText(this, "Could not download", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            Toast.makeText(this, "Downloaded.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Could not download", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void writeToFile(){
        File file = new File(U_FILE_NAME);
        boolean valid = true;

        if(!file.exists()){
            Toast.makeText(this, "The file does not exist. Cannot read.", Toast.LENGTH_SHORT).show();
        }
        else{
            // save pass fail data for
            Cursor data;
            ArrayList<String> courseCode = new ArrayList<>();
            ArrayList<Integer> passFailData = new ArrayList<>();
            String[] terms = {"F1", "W1", "F2", "W2", "F3", "W3", "F4", "W4", "F5", "W5"};
            for(int termNo = 0; termNo < terms.length; termNo++){
                data = db.sendPassFail(terms[termNo]);
                data.moveToFirst();
                boolean check = true;
                while (check) {
                    courseCode.add(data.getString(0));

                    if (data.getInt(1) == 1)
                        passFailData.add(1);
                    else
                        passFailData.add(0);

                    check = data.moveToNext();
                }
            }

            // save current database in case of undo needed
            saveToFile();

            // check validity of new entries
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(U_FILE_NAME)));
                String line;
                while(true){
                    try {
                        if ((line = br.readLine()) != null){
                            String[] strs = line.split("\\|");

                            if(10 > strs.length){
                                valid = false;
                                break;
                            }

                            // check course id
                            Pattern nullPattern = Pattern.compile("[n][u][l][l]");
                            Matcher nullMatcher;

                            Pattern pattern = Pattern.compile("[A-Z][A-Z][A-Z][A-Z]....");
                            Matcher matcher = pattern.matcher(strs[0]);
                            if(!matcher.find()){
                                valid = false;
                                break;
                            }

                            // check semester offered
                            pattern = Pattern.compile("[WFB]");
                            matcher = pattern.matcher(strs[2]);
                            if(!matcher.find()){
                                valid = false;
                                break;
                            }

                            // check ideal semester
                            pattern = Pattern.compile("[FW][1-5]");
                            matcher = pattern.matcher(strs[3]);
                            if(!matcher.find()){
                                valid = false;
                                break;
                            }

                            // pre-req1
                            pattern = Pattern.compile("[A-Z][A-Z][A-Z][A-Z]....");
                            matcher = pattern.matcher(strs[4]);
                            nullMatcher = nullPattern.matcher(strs[4]);
                            if(!matcher.find() && !nullMatcher.find()){
                                valid = false;
                                break;
                            }

                            // pre-req2
                            pattern = Pattern.compile("[A-Z][A-Z][A-Z][A-Z]....");
                            matcher = pattern.matcher(strs[5]);
                            nullMatcher = nullPattern.matcher(strs[5]);
                            if(!matcher.find() && !nullMatcher.find()){
                                valid = false;
                                break;
                            }

                            // to1
                            pattern = Pattern.compile("[A-Z][A-Z][A-Z][A-Z]....");
                            matcher = pattern.matcher(strs[6]);
                            nullMatcher = nullPattern.matcher(strs[6]);
                            if(!matcher.find() && !nullMatcher.find()){
                                valid = false;
                                break;
                            }

                            // to2
                            pattern = Pattern.compile("[A-Z][A-Z][A-Z][A-Z]....");
                            matcher = pattern.matcher(strs[7]);
                            nullMatcher = nullPattern.matcher(strs[7]);
                            if(!matcher.find() && !nullMatcher.find()){
                                valid = false;
                                break;
                            }

                            // to3
                            pattern = Pattern.compile("[A-Z][A-Z][A-Z][A-Z]....");
                            matcher = pattern.matcher(strs[8]);
                            nullMatcher = nullPattern.matcher(strs[8]);
                            if(!matcher.find() && !nullMatcher.find()){
                                valid = false;
                                break;
                            }

                            // to4
                            pattern = Pattern.compile("[A-Z][A-Z][A-Z][A-Z]....");
                            matcher = pattern.matcher(strs[9]);
                            nullMatcher = nullPattern.matcher(strs[9]);
                            if(!matcher.find() && !nullMatcher.find()){
                                valid = false;
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if(valid) { // all entries are of proper form
                // drop all tables and create new tables
                db. clearTables();

                // use data to populate tables
                String courseID, courseName, offered, semester, pr1, pr2, to1, to2, to3, to4 = null;
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(U_FILE_NAME)));
                    String line;
                    while(true){
                        try {
                            if ((line = br.readLine()) != null){
                                String[] strs = line.split("\\|");

                                courseID = strs[0];
                                courseName = strs[1];
                                offered = strs[2];
                                semester = strs[3];
                                pr1 = strs[4];
                                pr2 = strs[5];
                                to1 = strs[6];
                                to2 = strs[7];
                                to3 = strs[8];
                                to4 = strs[9];

                                db.setMaster(courseID, courseName, offered, pr1, pr2, to1, to2, to3, to4);
                                db.setSchedTables(courseID, semester);

                                boolean placed = false;
                                String trimmed, fromList;
                                for(int i = 0; i < courseCode.size(); i++){
                                    trimmed = courseID.trim();
                                    fromList = courseCode.get(i);
                                    if (trimmed.equals(fromList)){
                                        placed = true;
                                        db.setChangeRecord(courseID, passFailData.get(i));
                                    }
                                }
                                if(!placed){
                                    db.setChangeRecord(courseID, 0);
                                }
                            }
                            else{
                                break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                // rerun algorithm with static values from DatabaseHandler
                // call ahmeds here

                displayNotification();
                Toast.makeText(this, "Upload complete. Please review schedule.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Cannot read.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void displayNotification(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ChangeDatabase.this);

        builder.setTitle("Upload complete. Please review schedule.");
        builder.setMessage("To undo this action, copy the contents from downloadedDB.txt to uploadedDB.txt without the header and try again");

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
