package com.group1.EngPlan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.group1.EngPlan.Adapters.LoginDialog;
import com.group1.EngPlan.MakeAPlan.MakeAPlanYearScreen;

public class MainActivity extends AppCompatActivity implements LoginDialog.LoginListener{
    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainActivitybtn1 = (Button) findViewById(R.id.MainActivitybtn1);
        Button mainActivitybtn2 = (Button) findViewById(R.id.MainActivitybtn2);
        Button mainActivitybtn3 = (Button) findViewById(R.id.MainActivitybtn3);
        Button admin = (Button) findViewById(R.id.fileButton);

        mainActivitybtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent makeAPlan = new Intent(getApplicationContext(), MakeAPlanYearScreen.class);
                startActivity(makeAPlan);
            }
        });

        mainActivitybtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quickView = new Intent(getApplicationContext(), QuickView.class);
                startActivity(quickView);
            }
        });

        mainActivitybtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent central = new Intent(getApplicationContext(), CentralActivity.class);
                startActivity(central);
            }
        });

        admin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openDialog();
            }
        });
    }

    public void openDialog(){
        LoginDialog loginDialog = new LoginDialog();
        loginDialog.show(getSupportFragmentManager(), "Credential Dialog");
    }

    @Override
    public void applyText(String cred) {
        if(cred.equals("1234")){
            Intent changeDB = new Intent(getApplicationContext(), ChangeDatabase.class);
            startActivity(changeDB);
        }
        else{
            Toast.makeText(getApplicationContext(), "Incorrect Password.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        // do nothing
    }
}
