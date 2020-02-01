package com.group1.EngPlan;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.group1.EngPlan.DatabaseHandler.LOG_TAG;

public class QuickView extends AppCompatActivity {

    //DatabaseHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatabaseHandler myDB = new DatabaseHandler(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_view);

        ListView listView = (ListView) findViewById(R.id.ListViewIdeal);
        //myDB = new DatabaseHandler(this);
        Log.d(LOG_TAG, "Before method call");
        ArrayList<String> idealSchedule = new ArrayList<>();
        Cursor data = myDB.returnValue();
        Log.d(LOG_TAG, "Didn't crash yet");
        //if(data.getCount == 0){
          //  Toast.makeText(QuickView.this, "The Database Table is Empty. ",Toast.LENGTH_LONG).show();
        //}
        //else{
            while(data.moveToNext()){
                idealSchedule.add(data.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, idealSchedule);
                listView.setAdapter(listAdapter);
            }
        //}

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }
}
