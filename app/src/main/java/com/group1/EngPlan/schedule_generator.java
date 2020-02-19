package com.group1.EngPlan;

import android.database.Cursor;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;


import java.util.ArrayList;

public class schedule_generator {
    DatabaseHandler CDB = new DatabaseHandler(this);//reminder: fix this

    ArrayList<String> courseID = new ArrayList<>();
    ArrayList<String> courseStatus = new ArrayList<>();
    //ArrayList<ArrayList<String>> coursePreReqs = new ArrayList<>(3);
    Cursor data = CDB.getCourseData("1111");// Replace this DB method with one to get COURSEID, STATUS, OFFERED, PREREQS and TO for all courses

    public void add_data(){
        boolean check = true;

        data.moveToFirst();
        while(check){
            courseID.add(data.getString(0)); // check this with DB
            courseStatus.add(data.getString(1));
            check = data.moveToNext();
        }
    }

    public boolean check_new_student(){
        int count = 0;

        for(int i = 0; i<courseStatus.size(); i++){
            if(courseStatus.get(i) == "P" || courseStatus.get(i) == "F"){
                return false;
            }
        }
        return true;
    }

    public boolean check_pre_req(String courseID){
        int pos = courseID.indexOf(courseID);
        int count = 0;
        if(data.moveToPosition(pos)){
            String r1 = data.getString(2);
            String r2 = data.getString(3);

            if(r1.equals("NULL")){
                pos = courseID.indexOf(r1);
                if(courseStatus.get(pos).equals("P") || courseStatus.get(pos).equals("S")){
                    count++;
                }
            }
            else if(r2.equals("NULL")){
                pos = courseID.indexOf(r2);
                if(courseStatus.get(pos).equals("P") || courseStatus.get(pos).equals("S")){
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

        }

    }



}
