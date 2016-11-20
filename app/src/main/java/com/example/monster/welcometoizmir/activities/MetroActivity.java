package com.example.monster.welcometoizmir.activities;

import android.content.Intent;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.monster.welcometoizmir.R;
import com.example.monster.welcometoizmir.UtilTransportation;
import com.example.monster.welcometoizmir.classes.Bus;
import com.example.monster.welcometoizmir.classes.MetroStop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class MetroActivity extends AppCompatActivity {

    ListView lstStops;
    MetroStopListAdapter stopListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro);

        getSupportActionBar().setTitle("Metro Durakları");

        lstStops = (ListView)findViewById(R.id.lstMetroStops);

        populateMetroStopList();
    }


    public void populateMetroStopList() {
        ArrayList<MetroStop> metroStops = new ArrayList<>();
        for (int key=1; key<=4; key++) {
            for (int i = 0; i < MainActivity.metroLines.get(key).size(); i++) {
                metroStops.add(MainActivity.metroLines.get(key).get(i));
            }
        }
        stopListAdapter = new MetroStopListAdapter(metroStops);
        lstStops.setAdapter(stopListAdapter);
    }

    public class MetroStopListAdapter extends ArrayAdapter<MetroStop> {

        public MetroStopListAdapter(ArrayList<MetroStop> stops) {
            super(getApplicationContext(), 0, stops);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            if (view == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_metrostop_list, parent, false);
            }

            MetroStop metroStop = getItem(position);

            PercentRelativeLayout pnlMain = (PercentRelativeLayout) view.findViewById(R.id.pnlmain);
            TextView txtStopName = (TextView)view.findViewById(R.id.txtStopName);
            ImageView imgLineColor1 = (ImageView) view.findViewById(R.id.imgLineColor1);
            ImageView imgLineColor2 = (ImageView) view.findViewById(R.id.imgLineColor2);
            ImageView imgIcon1 = (ImageView) view.findViewById(R.id.imgIconfield1);
            ImageView imgIcon2 = (ImageView) view.findViewById(R.id.imgIconfield2);
            ImageView imgIcon3 = (ImageView) view.findViewById(R.id.imgIconfield3);

            imgIcon1.setBackgroundResource(R.color.transparent);
            if (metroStop.isBusTransferEnable()){
                imgIcon1.setBackgroundResource(R.drawable.ic_bus);
            }

            Log.i("metrostop", metroStop.getName());

            txtStopName.setText(metroStop.getName());
            imgLineColor1.setBackgroundResource(metroStop.getLineColorID1());
            if (metroStop.getLineId2() != 0){
                imgLineColor2.setBackgroundResource(metroStop.getLineColorID2());
            }
            else{
                imgLineColor2.setBackgroundResource(R.color.transparent);
            }
            return view;
        }
    }

}
