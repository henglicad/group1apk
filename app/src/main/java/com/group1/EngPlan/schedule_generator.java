package com.group1.EngPlan;

import android.content.Context;
import android.database.Cursor;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class schedule_generator extends AppCompatActivity {
    public static final String LOG_TAG = schedule_generator.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //test();
    }

    //DatabaseHandler CDB = new DatabaseHandler(this);//reminder: fix this

    ArrayList<String> courseID = new ArrayList<>();
    ArrayList<String> courseStatus = new ArrayList<>();
    //ArrayList<ArrayList<String>> coursePreReqs = new ArrayList<>(3);
    Cursor data;// Replace this DB method with one to get COURSEID, STATUS, OFFERED, PREREQS and TO for all courses

    public schedule_generator( DatabaseHandler db){
        data = db.getSchedData();
    }

    public void test(){
        data.moveToFirst();

            for(data.moveToFirst(); !data.isAfterLast(); data.moveToNext()){
                for(int i = 0; i<9; i++){
                    if(data.getString(i) != null){
                        Log.d(LOG_TAG, data.getString(i));
                    }
                    else{
                        Log.d(LOG_TAG, "null");
                    }

                }
            }
    }

    public void add_data(){
        boolean check = true;

        data.moveToFirst();
        while(check){
            courseID.add(data.getString(0)); // check this with DB
            courseStatus.add(data.getString(8));
            check = data.moveToNext();
        }
    }

    /*public boolean check_new_student(){
        int count = 0;

        for(int i = 0; i<courseStatus.size(); i++){
            if(courseStatus.get(i) == "P" || courseStatus.get(i) == "F"){
                return false;
            }
        }
        return true;
    }*/

    public boolean check_pre_req(String courseID){
        int pos = courseID.indexOf(courseID);
        int count = 0;
        String r1, r2;
        if(data.moveToPosition(pos)) {
            if(data.getString(2) == null){
                r1 = "NULL";
            }
            else{
                r1 = data.getString(2);
            }

            if(data.getString(3) == null){
                r2 = "NULL";
            }
            else{
                r2 = data.getString(3);
            }


            if (r1.equals("NULL")) {
                count++;
            } else {
                pos = courseID.indexOf(r1);
                if (courseStatus.get(pos).equals("P") || courseStatus.get(pos).equals("S")) {
                    count++;
                }
            }


            if (r2.equals("NULL")) {
                count++;
            }
            else {
                pos = courseID.indexOf(r2);
                if (courseStatus.get(pos).equals("P") || courseStatus.get(pos).equals("S")) {
                    count++;
                }
            }

        }

        if(count == 2){
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<String> subList1 (ArrayList<String> masterID ){
        ArrayList<String> s = new ArrayList<>();

        for(int i= 0; i< masterID.size(); i++){
            if(check_pre_req(masterID.get(i))){
                s.add(masterID.get(i));
            }
        }

        return s;
    }

    public ArrayList<String> getFall(ArrayList<String> sub){
        ArrayList<String> fall = new ArrayList<>();

        for(int i = 0; i<sub.size(); i++){
            data.moveToPosition(data.getColumnIndex(sub.get(i)));
            if(data.getString(1).equals("F")){
                fall.add(sub.get(i));
            }
        }

        return fall;

    }

    public ArrayList<String> getWinter(ArrayList<String> sub){
        ArrayList<String> Winter = new ArrayList<>();

        for(int i = 0; i<sub.size(); i++){
            data.moveToPosition(data.getColumnIndex(sub.get(i)));
            if(data.getString(1).equals("W")){
                Winter.add(sub.get(i));
            }
        }

        return Winter;

    }

    public ArrayList<String> order(ArrayList<String> x){
        int a = -1;
        int count1=0;
        int count2 = 0;
        String m;
        while(a<0){
            for(int i =0; i<x.size(); i++){
                if(i == x.size()){
                    a = 1;
                    break;
                }
                else{
                    data.moveToPosition(data.getColumnIndex(x.get(i)));
                    for(int k = 4; k<8; k++){
                        if(data.getString(k) != null){
                            count1++;
                        }
                    }

                    data.moveToPosition(data.getColumnIndex(x.get(i+1)));
                    for(int k = 4; k<8; k++){
                        if(data.getString(k) != null){
                            count2++;
                        }
                    }

                    if(count1>count2){
                        m = x.get(i+1);
                        x.set(i+1, x.get(i));
                        x.set(i, m);
                        break;
                    }
                }
            }
        }

        return x;
    }

    //Need to seperate the semester lists into different years.



}
