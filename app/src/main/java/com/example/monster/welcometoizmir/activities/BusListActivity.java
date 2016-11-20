package com.example.monster.welcometoizmir.activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

import com.example.monster.welcometoizmir.R;
import com.example.monster.welcometoizmir.classes.Bus;
import com.example.monster.welcometoizmir.classes.BusStop;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class BusListActivity extends AppCompatActivity {
    private boolean isDataDownloaded;

    private HashMap<String, Bus> buses;
    private HashMap<String, BusStop> busStops;

    private ListView lstBuses;
    private BusListAdapter busListAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_buses);

        getSupportActionBar().setTitle("İzmir Otobüsler");

        lstBuses = (ListView)findViewById(R.id.lstBuses);

        loadData();
        Log.i("isdatadownloaded", String.valueOf(isDataDownloaded));
        if (isDataDownloaded){
            populateBusList();
        }
        else {
            new GetBusesTask().execute();
        }

    }

    public void populateBusList() {
        busListAdapter = new BusListAdapter(buses);
        lstBuses.setAdapter(busListAdapter);
    }

    public class BusListAdapter extends BaseAdapter {
        private ArrayList<Bus> mData;

        public BusListAdapter(HashMap<String, Bus> map) {
            mData = new ArrayList();
            for (String key: map.keySet()) {
                mData.add(map.get(key));
            }

            // Sorting
            Collections.sort(mData, new Comparator<Bus>() {
                @Override
                public int compare(Bus bus1, Bus bus2)
                {

                    return  bus1.getIntegerId() - bus2.getIntegerId();
                }
            });
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

    public class GetBusesTask extends AsyncTask<String, String, String> {
        protected void onPreExecute() {
            buses = new HashMap<>();
            busStops = new HashMap<>();
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {
            getAllBuses();
            return null;
        }

        protected void onPostExecute(String result) {
            isDataDownloaded = true;
            saveData();
            populateBusList();

            super.onPostExecute(result);
        }


        public void getAllBuses() {
            String url_all_buses = "http://www.eshot.gov.tr/";
            try {
                Document doc = Jsoup.connect(url_all_buses).get();
                //Log.i("document", doc.toString());
                Elements rows = doc.select("#frmUlasimSaatleri").select("option");

                for (int i = 1; i < rows.size(); i++) {
                    //first row is the col names so skip it.

                    Element row = rows.get(i);
                    Elements cols = row.select("option");

                    for (int j = 0; j < cols.size(); j++) {
                        Bus bus = new Bus();


                        bus.setBusId(cols.get(j).val());/// hat id
                        Log.i("BUS", bus.getBusId());

                        getBusHoursAndStops(bus);
                        buses.put(bus.getBusId(), bus);

                        //String[] stops = cols.get(j).text().split("-");
                        // stops[0] stops[1] ilk ve son durak ama çekmedim otobüs saatlerini çekerken alıcam.
                        //Log.d(bus.getName(), bus.getFirst_stop() + "," + bus.getLast_stop());
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void getBusHoursAndStops(Bus bus) {
            try {
                String url = "http://www.eshot.gov.tr/tr/UlasimSaatleri/" + bus.getBusId() + "/288";

                Document doc = Jsoup.connect(url)
                        // and other hidden fields which are being passed in post request.
                        .get();

                Elements elementStops = doc.select("#frmDuraklar").select("li");

                ////OTONÜSÜN GEÇTİĞİ DURAKLAR
                for (int i = 0; i < elementStops.size(); i++) {
                    ///stop kayıtlıysa al kayıtlı değilse yeni stop yarat
                    BusStop stop = null;
                    if (busStops.get(elementStops.get(i).id()) == null) {
                        stop = new BusStop();
                    } else {
                        stop = busStops.get(elementStops.get(i).id());
                    }
                    stop.setId(elementStops.get(i).id());
                    stop.setName(elementStops.get(i).text());

                    ///otobüs bu duraktan geçiyor
                    stop.getBuses().add(bus);
                    bus.getStops().add(stop);
                }

                //OTOBÜS İLK SON DURAK KALKIŞ SAATLERİ
                //hafta içi
                    getHours(doc, bus, "Hafta İçi", "#eleman1");
                //cumartesi
                    getHours(doc, bus, "Cumartesi", "#eleman2");
                //hafta sonu
                    getHours(doc, bus, "Pazar", "#eleman3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void getHours(Document doc,Bus bus , String dayValue, String dataValue){
            Elements elementsHoursWeekdays = doc.select(dataValue).select("li");

            ArrayList firstStopHours = new ArrayList<String>();
            ArrayList lastStopHours = new ArrayList<String>();
            boolean isFirstStop = false;
            for (int i = 0; i < elementsHoursWeekdays.size(); i++) {
                if (elementsHoursWeekdays.get(i).className().equals("")) {
                    isFirstStop = !isFirstStop;
                }

                if (isFirstStop) {
                    firstStopHours.add(elementsHoursWeekdays.get(i).text());
                } else {
                    lastStopHours.add(elementsHoursWeekdays.get(i).text());
                }
            }
            bus.getFirstStopMoveHours().put(dayValue, firstStopHours);
            bus.getLastStopMoveHours().put(dayValue, lastStopHours);
        }
    }

    public void saveData() {
        try {
            String fileName = "buses&stops.ser";
            FileOutputStream fos = this.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            Log.i("saving file", String.valueOf(isDataDownloaded));
            os.writeObject(this.buses);
            os.writeObject(this.busStops);
            os.writeBoolean(this.isDataDownloaded);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {
            String fileName = "buses&stops.ser";
            FileInputStream fis = this.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            this.buses = (HashMap<String, Bus>) is.readObject();
            this.busStops =  (HashMap<String, BusStop>) is.readObject();
            this.isDataDownloaded = is.readBoolean();
            is.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
