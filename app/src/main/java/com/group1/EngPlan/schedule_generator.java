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

public class schedule_generator  {
    public static final String LOG_TAG = schedule_generator.class.getSimpleName();
    Cursor data;

    ArrayList<String> courseID = new ArrayList<>();
    ArrayList<String> courseStatus = new ArrayList<>();
    DatabaseHandler CDB;

    public schedule_generator(DatabaseHandler db){
        CDB = db;
        data = CDB.getSchedData();
        //test();
    }

    public void main(int num_of_courses, String sem, int year){
        add_data();

        ArrayList<String> MasterList = getMasterList();

        while(checkMaster(MasterList)){
            ArrayList<String> sub = subList1(MasterList);

            ArrayList<String> Fall = getFall(sub);
            Fall = order(Fall);

            ArrayList<String> Winter = getWinter(sub);
            Winter = order(Winter);

            saveInDB(Fall, Winter, sem, year, num_of_courses);

            //ArrayList<ArrayList<String>> fallSems = semSplit(Fall, num_of_courses);
            //ArrayList<ArrayList<String>> WintSems = semSplit(Winter, num_of_courses);
        }
    }

    /*public void test(){
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
    }*/

    public void add_data(){
        boolean check = true;

        data.moveToFirst();
        while(check){
            courseID.add(data.getString(0)); // check this with DB
            courseStatus.add(Integer.toString(data.getInt(8)));
            check = data.moveToNext();
        }
    }

    public ArrayList<String> getMasterList(){
        ArrayList<String> MasterList = new ArrayList<>();

        for(int i = 0; i<courseStatus.size(); i++){
            if(courseStatus.get(i).equals("0")){
                MasterList.add(courseID.get(i));
            }
        }

        return MasterList;
    }

    public boolean checkMaster(ArrayList<String> Master){


        for(int i = 0; i<Master.size(); i++){
            if(courseStatus.get(courseID.indexOf(Master.get(i))).equals("0"))
            return false;
        }
        return true;
    }

    public boolean check_pre_req(String course){
        int pos = courseID.indexOf(course);
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
                if (courseStatus.get(pos).equals("1")) {
                    count++;
                }
            }


            if (r2.equals("NULL")) {
                count++;
            }
            else {
                pos = courseID.indexOf(r2);
                if (courseStatus.get(pos).equals("1")) {
                    count++;
                }
            }

        }

        return count == 2;
    }

    public ArrayList<String> subList1 (ArrayList<String> masterID ){
        ArrayList<String> s = new ArrayList<>();

        for(int i= 0; i< masterID.size(); i++){
            if(check_pre_req(masterID.get(i))){
                s.add(masterID.get(i));
                courseStatus.set(i, "1");
            }
        }

        return s;
    }

    public ArrayList<String> getFall(ArrayList<String> sub){
        ArrayList<String> fall = new ArrayList<>();

        for(int i = 0; i<sub.size(); i++){
            data.moveToPosition(data.getColumnIndex(sub.get(i)));
            if(data.getString(1).equals("F") || data.getString(0).equals("COOP1000")){
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
            for(int i =0; i<=x.size(); i++){
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

                    if(count1<count2){
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


    public void saveInDB(ArrayList<String> fall, ArrayList<String> winter, String sem, int year, int Cnum){
        if(sem.equals("W"))
            year ++;
        int num = Cnum;


        while(!fall.isEmpty() || !winter.isEmpty()){
            if(sem.equals("W") && !winter.isEmpty()){
                if(num > winter.size())
                    num = winter.size();

                for(int i = 0; i<num; i++){
                    CDB.setSavedSched(winter.get(0), sem+year);
                    winter.remove(0);
                }
                sem = "F";
                year ++;
            }
            else if(sem.equals("F") && !fall.isEmpty()){
                if(num > fall.size())
                    num = fall.size();

                for(int i = 0; i<num; i++){
                    CDB.setSavedSched(fall.get(0), sem+year);
                    fall.remove(0);
                }
                sem = "W";
            }

            num = Cnum;
        }
    }
}
