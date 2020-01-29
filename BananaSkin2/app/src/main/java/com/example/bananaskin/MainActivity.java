package com.example.bananaskin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.bananaskin.R.id.loginBtn;

public class MainActivity extends AppCompatActivity {

  //  DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   myDb =  new DatabaseHelper(this);

        Button loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNavDrawer = new Intent(getApplicationContext(),NavDrawerAct.class);
                startActivity(startNavDrawer);
            }
        });
    }
}
