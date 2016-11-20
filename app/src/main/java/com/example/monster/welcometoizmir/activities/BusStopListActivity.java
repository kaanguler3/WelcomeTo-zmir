package com.example.monster.welcometoizmir.activities;

import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.monster.welcometoizmir.R;
import com.example.monster.welcometoizmir.classes.Bus;
import com.example.monster.welcometoizmir.classes.BusStop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BusStopListActivity extends AppCompatActivity {
    StopListAdapter stopListAdapter;
    ListView lstStops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busstop_list);

        getSupportActionBar().setTitle("Otobüs durakları");

        lstStops = (ListView)findViewById(R.id.lstStops);

        populateStopList();
    }


    public void populateStopList() {
        ArrayList<BusStop> mData = new ArrayList();
        for (String key: MainActivity.busStops.keySet()) {
            System.out.println(MainActivity.busStops.get(key).getName());
            mData.add(MainActivity.busStops.get(key));
        }

        Collections.sort(mData, new Comparator<BusStop>() {
            @Override
            public int compare(BusStop o1, BusStop o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        stopListAdapter = new StopListAdapter(mData);
        lstStops.setAdapter(stopListAdapter);
    }

    public class StopListAdapter extends ArrayAdapter<BusStop> {

        public StopListAdapter(ArrayList<BusStop> stops) {

            super(getApplicationContext(), 0, stops);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            if (view == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_busstop_list, parent, false);
            }

            BusStop busStop = getItem(position);


            PercentRelativeLayout pnlMain = (PercentRelativeLayout) view.findViewById(R.id.pnlmain);
            TextView txtStopName = (TextView)view.findViewById(R.id.txtStopName);
            ImageView imgStop = (ImageView) view.findViewById(R.id.imgStop);
            ImageView imgTransfer1 = (ImageView) view.findViewById(R.id.imgTransfer1);

            imgStop.setBackgroundResource(busStop.getIconId());
            txtStopName.setText(busStop.getName());
            imgTransfer1.setBackgroundResource(R.color.transparent);
            if (busStop.isMetroTransferEnable()){
                imgTransfer1.setBackgroundResource(R.drawable.ic_metro);
            }

            return view;
        }
    }
}
