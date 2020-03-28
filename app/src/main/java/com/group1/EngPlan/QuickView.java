package com.group1.EngPlan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.Adapters.CourseAdapter;
import com.group1.EngPlan.Backend.DatabaseHandler;

import java.util.ArrayList;

public class QuickView extends AppCompatActivity {

    ArrayList<String> idealScheduleCode = new ArrayList<>();
    ArrayList<String> idealScheduleName = new ArrayList<>();
    boolean fromMenu = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHandler myDB = new DatabaseHandler(this);

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        fromMenu = intent.getBooleanExtra("Menu", false);

        setContentView(R.layout.activity_quick_view);

        ListView listView = (ListView) findViewById(R.id.ListViewIdeal);
        String[] terms = {"F1", "W1", "F2", "W2", "F3", "W3", "F4", "W4", "F5", "W5"};
        int termNo = 0;

        while(termNo < terms.length) {

            Cursor data = myDB.fillQuickView(terms[termNo]);
            idealScheduleName.add(terms[termNo]);
            idealScheduleCode.add(terms[termNo]);

            int x = data.getCount();
            if(x != 0) {
                data.moveToFirst();
                boolean check = true;
                while (check) {
                    idealScheduleCode.add(data.getString(0));
                    check = data.moveToNext();
                }

                data.moveToFirst();
                check = true;
                while (check) {
                    idealScheduleName.add(data.getString(1));
                    check = data.moveToNext();
                }
            }
            termNo++;
        }

        for(int i = 0; i < idealScheduleCode.size()-1; i++){
            if((getItemViewType(i) == 0) && (getItemViewType(i+1) == 0)){
                idealScheduleName.remove(i);
                idealScheduleCode.remove(i);
                i--;
            }
        }
        int temp = idealScheduleCode.size();
        if(getItemViewType(temp-1) == 0){
            idealScheduleName.remove(temp-1);
            idealScheduleCode.remove(temp-1);
        }

        CourseAdapter courseAdapter = new CourseAdapter(this, idealScheduleCode, idealScheduleName);
        listView.setAdapter(courseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = idealScheduleCode.get(position);
                if(getItemViewType(position) == 1){
                    Intent showCourseInfo = new Intent(getApplicationContext(), CourseDetails.class);
                    showCourseInfo.putExtra("com.group1.INDEX", data);
                    startActivity(showCourseInfo);

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(fromMenu){
            Intent intent = new Intent(getApplicationContext(), CentralActivity.class);
            startActivity(intent);
        }
        else {
            super.onBackPressed();
        }
    }

    public int getItemViewType(int position){
        if((idealScheduleName.get(position).equals("F1")) || (idealScheduleName.get(position).equals("W1")) ||(idealScheduleName.get(position).equals("F2")) || (idealScheduleName.get(position).equals("W2")) ||
                (idealScheduleName.get(position).equals("F3")) || (idealScheduleName.get(position).equals("W3")) || (idealScheduleName.get(position).equals("F4")) || (idealScheduleName.get(position).equals("W4")) ||
                (idealScheduleName.get(position).equals("F5")) || (idealScheduleName.get(position).equals("W5"))||(idealScheduleName.get(position).equals("F6")) || (idealScheduleName.get(position).equals("W6")) ||
                (idealScheduleName.get(position).equals("F7")) || (idealScheduleName.get(position).equals("W7"))){
            return 0;}
        return 1;
    }

}
