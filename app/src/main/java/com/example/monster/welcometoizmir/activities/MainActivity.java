package com.example.monster.welcometoizmir.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.monster.welcometoizmir.R;


public class MainActivity extends AppCompatActivity {
    Button btnBusList, btnMetro;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBusList = (Button)findViewById(R.id.btnBuses);
        btnMetro = (Button)findViewById(R.id.btnMetro);

        btnBusList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusListActivity.class);
                startActivity(intent);
            }
        });


        btnMetro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MetroActivity.class);
                startActivity(intent);
            }
        });
    }
}