package com.example.monster.welcometoizmir.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.monster.welcometoizmir.R;
import com.example.monster.welcometoizmir.classes.Bus;

import java.util.ArrayList;

public class BusHoursAndStopsActivity extends AppCompatActivity {
    private Bus bus;

    private TextView txtStopNames;
    private TabHost tabHost;
    private ListView lstWeekDaysFirstStation, lstWeekDaysLastStation;
    private Button btnTab1, btnTab2, btnTab3;
    private BusHoursAdapter busHoursListAdapter1, busHoursListAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_hours_and_stops);

        bus = (Bus) getIntent().getSerializableExtra("bus");
        getSupportActionBar().setTitle("OTOBÜS : " + bus.getBusId());


        txtStopNames = (TextView) findViewById(R.id.txtStopNames);
        for (int i=0; i<bus.getStops().size(); i++){
            txtStopNames.setText(txtStopNames.getText().toString() + bus.getStops().get(i).getName() + " - ");
        }

        lstWeekDaysFirstStation = (ListView)findViewById(R.id.lstWeekDaysFirstStation);
        lstWeekDaysLastStation = (ListView)findViewById(R.id.lstWeekDaysLastStation);

        btnTab1 = (Button)findViewById(R.id.tab1);
        btnTab2 = (Button)findViewById(R.id.tab2);
        btnTab3 = (Button)findViewById(R.id.tab3);

        btnTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateBusHours("Hafta İçi");
                btnTab1.setBackgroundResource(android.R.drawable.button_onoff_indicator_on);
                btnTab2.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
                btnTab3.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
            }
        });

        btnTab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateBusHours("Cumartesi");
                btnTab1.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
                btnTab2.setBackgroundResource(android.R.drawable.button_onoff_indicator_on);
                btnTab3.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
            }
        });

        btnTab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateBusHours("Pazar");
                btnTab1.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
                btnTab2.setBackgroundResource(android.R.drawable.button_onoff_indicator_off);
                btnTab3.setBackgroundResource(android.R.drawable.button_onoff_indicator_on);
            }
        });

        populateBusHours("Hafta İçi");
    }

    public void populateBusHours(String day) {
        busHoursListAdapter1 = new BusHoursAdapter(bus.getFirstStopMoveHours().get(day));
        lstWeekDaysFirstStation.setAdapter(busHoursListAdapter1);

        busHoursListAdapter2 = new BusHoursAdapter(bus.getLastStopMoveHours().get(day));
        lstWeekDaysLastStation.setAdapter(busHoursListAdapter2);
    }

    public class BusHoursAdapter extends ArrayAdapter<String> {

        public BusHoursAdapter(ArrayList<String> hours) {
            super(getApplicationContext(), 0, hours);
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_of_bus_hour_list, parent, false);
            }

             String hour = getItem(position);

            TextView txtTitle = (TextView)convertView.findViewById(R.id.txtHour);
            txtTitle.setText(hour);

            return convertView;
        }
    }
}
