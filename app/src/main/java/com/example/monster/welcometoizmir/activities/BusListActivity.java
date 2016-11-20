package com.example.monster.welcometoizmir.activities;

import android.content.Intent;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.monster.welcometoizmir.GetBusesTask;
import com.example.monster.welcometoizmir.R;
import com.example.monster.welcometoizmir.classes.Bus;
import com.example.monster.welcometoizmir.classes.BusStop;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class BusListActivity extends AppCompatActivity {


    //private HashMap<String, Bus> buses;
    //private HashMap<String, BusStop> busStops;

    private ListView lstBuses;
    private BusListAdapter busListAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_buses);

        getSupportActionBar().setTitle("İzmir Otobüsler");

        lstBuses = (ListView)findViewById(R.id.lstBuses);


        populateBusList();
    }

    public void populateBusList() {
        busListAdapter = new BusListAdapter(MainActivity.buses);
        lstBuses.setAdapter(busListAdapter);
    }

    public class BusListAdapter extends BaseAdapter {
        private ArrayList<Bus> mData;

        public BusListAdapter(HashMap<String, Bus> map) {
            mData = new ArrayList();
            for (String key: map.keySet()) {
                mData.add(map.get(key));
            }
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Bus getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO implement you own logic with ID
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            if (view == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_bus_list, parent, false);
            }

            final Bus bus = getItem(position);

            PercentRelativeLayout pnlMain = (PercentRelativeLayout) view.findViewById(R.id.pnlmain);
            TextView txtBusId = (TextView)view.findViewById(R.id.txtBusId);
            TextView txtStopNames = (TextView)view.findViewById(R.id.txtStopNames);

            txtBusId.setText(bus.getBusId());
            txtStopNames.setText(bus.getStops().get(0).getName() + " - " +
                    bus.getStops().get(bus.getStops().size()-1).getName());

            pnlMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), BusHoursAndStopsActivity.class);
                    intent.putExtra("bus", bus);
                    startActivity(intent);
                }
            });
            return view;
        }
    }


}
