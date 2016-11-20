package com.example.monster.welcometoizmir;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.monster.welcometoizmir.activities.MainActivity;
import com.example.monster.welcometoizmir.classes.Bus;
import com.example.monster.welcometoizmir.classes.BusStop;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MONSTER on 20.11.2016.
 */

public class GetBusesTask extends AsyncTask<String, String, String> {
    Activity activity;
    public GetBusesTask(Activity activity) {
        this.activity = activity;
    }

    protected void onPreExecute() {
        MainActivity.buses = new HashMap<>();
        MainActivity.busStops = new HashMap<>();
        super.onPreExecute();
    }

    protected String doInBackground(String... params) {
        getAllBuses();
        return null;
    }

    protected void onPostExecute(String result) {
        saveData();
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
                    MainActivity.buses.put(bus.getBusId(), bus);

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
                BusStop busStop = null;
                if (MainActivity.busStops.get(elementStops.get(i).id()) == null) {
                    busStop = new BusStop();
                } else {
                    busStop = MainActivity.busStops.get(elementStops.get(i).id());
                }
                busStop.setId(elementStops.get(i).id());
                busStop.setName(elementStops.get(i).text());

                ///otobüs bu duraktan geçiyor
                Log.i("stop:", busStop.getName());
                busStop.getBuses().add(bus);
                bus.getStops().add(busStop);
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

    public void saveData() {
        try {
            String fileName = "buses&stops.ser";
            FileOutputStream fos = activity.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);

            os.writeObject(MainActivity.buses);
            os.writeObject(MainActivity.busStops);
            os.writeBoolean(true); /// is data downloaded.// yes!
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}