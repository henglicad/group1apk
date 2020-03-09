package com.group1.EngPlan;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.group1.EngPlan.Adapters.CourseAdapter;
import com.group1.EngPlan.MakeAPlan.PassFailScreen;

import java.util.ArrayList;

public class CentralActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String LOG_DATA = CentralActivity.class.getSimpleName();
    private DrawerLayout drawer;
    ArrayList<String> courseCode = new ArrayList<>();
    ArrayList<String> courseName = new ArrayList<>();
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_central);

        Toolbar toolbar = findViewById(R.id.central_screen_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.central_screen_drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayList();

    }

    public void displayList(){

        final DatabaseHandler myDB = new DatabaseHandler(this);
        ListView listView = (ListView) findViewById(R.id.central_activity_list_view);

        String[] terms = {"F1", "W1", "F2", "W2", "F3", "W3", "F4", "W4", "F5", "W5", "F6", "W6", "F7", "W7"};
        int termNo = 0;
        while(termNo < terms.length) {

            Cursor data = myDB.getSavedSched(terms[termNo]);
            courseName.add(terms[termNo]);
            courseCode.add(terms[termNo]);

            data.moveToFirst();
            String s;
            //boolean check = true;
            /*while (check){
                s = DatabaseUtils.dumpCurrentRowToString(data);
                Log.d(LOG_DATA, s);
                check = data.moveToNext();
            }*/

            data.moveToFirst();
            boolean check = true;
            if(data.getCount() != 0){
                while (check) {
                    s = DatabaseUtils.dumpCurrentRowToString(data);
                    Log.d(LOG_DATA, s);
                    courseCode.add(data.getString(0));
                    courseName.add(data.getString(1));
                    check = data.moveToNext();
                }
            }

            termNo++;
        }

        CourseAdapter courseAdapter = new CourseAdapter(this, courseCode, courseName);
        listView.setAdapter(courseAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = courseCode.get(position);
                if((courseName.get(position) == "F1") || (courseName.get(position) == "W1") ||(courseName.get(position) == "F2") || (courseName.get(position) == "W2") ||
                        (courseName.get(position) == "F3") || (courseName.get(position) == "W3") || (courseName.get(position) == "F4") || (courseName.get(position) == "W4") ||
                        (courseName.get(position) == "F5") || (courseName.get(position) == "W5")||(courseName.get(position) == "F6") || (courseName.get(position) == "W6")||
                        (courseName.get(position) == "F7") || (courseName.get(position) == "W7")){
                }
                else {
                    Intent showCourseInfo = new Intent(getApplicationContext(), CourseDetails.class);
                    showCourseInfo.putExtra("com.group1.INDEX", data);
                    startActivity(showCourseInfo);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (item.isChecked()){
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }

        if(id == R.id.nav_quick_view){
            startActivity(new Intent (getApplicationContext(), QuickView.class));
        }
        else if(id == R.id.nav_PF_view){
            startActivity(new Intent (getApplicationContext(), PassFailScreen.class));
        }
        /*else if(id == R.id.nav_schedule_change_view){
            startActivity(new Intent (getApplicationContext(), ScheduleChange.class));
        }*/

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
