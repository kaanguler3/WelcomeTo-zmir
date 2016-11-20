package com.example.monster.welcometoizmir.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.monster.welcometoizmir.GetBusesTask;
import com.example.monster.welcometoizmir.R;
import com.example.monster.welcometoizmir.UtilTransportation;
import com.example.monster.welcometoizmir.classes.Bus;
import com.example.monster.welcometoizmir.classes.BusStop;
import com.example.monster.welcometoizmir.classes.MetroStop;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private boolean isBusDataDownloaded;
    Button btnBusList, btnMetro, btnBusStops;

    public static HashMap<String, Bus> buses;
    public static HashMap<String, BusStop> busStops;
    public static HashMap<Integer, ArrayList<MetroStop>> metroLines;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metroLines = UtilTransportation.initializeMetros();
        loadData();
        if (!isBusDataDownloaded){
            new GetBusesTask(this).execute();
        }
        UtilTransportation.initializeIsTransferValuesForStops();

        btnBusList = (Button)findViewById(R.id.btnBuses);
        btnMetro = (Button)findViewById(R.id.btnMetro);
        btnBusStops = (Button)findViewById(R.id.btnStops);

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

        btnBusStops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusStopListActivity.class);
                startActivity(intent);
            }
        });

    }

    public void loadData() {
        try {
            String fileName = "buses&stops.ser";
            FileInputStream fis = this.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            MainActivity.buses = (HashMap<String, Bus>) is.readObject();
            MainActivity.busStops =  (HashMap<String, BusStop>) is.readObject();
            this.isBusDataDownloaded = is.readBoolean();
            is.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}