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
import com.example.monster.welcometoizmir.classes.Bus;
import com.example.monster.welcometoizmir.classes.MetroStop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class MetroActivity extends AppCompatActivity {
    HashMap<Integer, ArrayList<MetroStop>> metroLines;

    ListView lstStops;
    MetroStopListAdapter stopListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro);

        lstStops = (ListView)findViewById(R.id.lstMetroStops);
        initializeMetros();

        populateMetroStopList();
    }


    public void populateMetroStopList() {
        ArrayList<MetroStop> metroStops = new ArrayList<>();
        for (int key=1; key<=4; key++) {
            for (int i = 0; i < metroLines.get(key).size(); i++) {
                metroStops.add(metroLines.get(key).get(i));
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

            if (metroStop.getIconIDs().size() == 1){
                imgIcon1.setBackgroundResource(metroStop.getIconIDs().get(0));
                imgIcon2.setBackgroundResource(R.color.transparent);
                imgIcon3.setBackgroundResource(R.color.transparent);
            }
            else if(metroStop.getIconIDs().size() == 2){
                imgIcon1.setBackgroundResource(metroStop.getIconIDs().get(0));
                imgIcon2.setBackgroundResource(metroStop.getIconIDs().get(1));
                imgIcon3.setBackgroundResource(R.color.transparent);
            }
            else if(metroStop.getIconIDs().size() == 3){
                imgIcon1.setBackgroundResource(metroStop.getIconIDs().get(0));
                imgIcon2.setBackgroundResource(metroStop.getIconIDs().get(1));
                imgIcon3.setBackgroundResource(metroStop.getIconIDs().get(2));
            }
            else{
                imgIcon1.setBackgroundResource(R.color.transparent);
                imgIcon2.setBackgroundResource(R.color.transparent);
                imgIcon3.setBackgroundResource(R.color.transparent);
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

    public void initializeMetros(){
        metroLines = new HashMap<>();
        ///1-kırmızı hat  ,  2-mavi   ,   3-açık yeşil   ,  4-koyu yeşil"

        Log.i("aq", "aq all");
        ///METRO HATTI 1 (KIRMIZI)
        MetroStop metroStop1 = new MetroStop("Fahrettin Altay", 1);
        metroStop1.setBusTransferEnable(true);
        metroStop1.setTramwayTransferEnable(true);

        MetroStop metroStop2 = new MetroStop("Poligon", 1);
        MetroStop metroStop3 = new MetroStop("Göztepe", 1);
        MetroStop metroStop4 = new MetroStop("Hatay", 1);
        MetroStop metroStop5 = new MetroStop("İzmirspor", 1);

        MetroStop metroStop6 = new MetroStop("Üçyol", 1);
        metroStop6.setBusTransferEnable(true);

        MetroStop metroStop7 = new MetroStop("Konak", 1);
        metroStop7.setBusTransferEnable(true);
        metroStop7.setTramwayTransferEnable(true);
        metroStop7.setFerryTransferEnable(true);

        MetroStop metroStop8 = new MetroStop("Çankaya", 1);

        MetroStop metroStop9 = new MetroStop("Basmane", 1);
        metroStop9.setRailwayTrasnferEnable(true);

        MetroStop metroStop10 = new MetroStop("Hilal", 1, 2);
        metroStop10.setTramwayTransferEnable(true);
        metroStop10.setMetroTransferEnable(true);

        MetroStop metroStop11 = new MetroStop("Halkpınar", 1, 2);
        metroStop11.setBusTransferEnable(true);
        metroStop11.setTramwayTransferEnable(true);
        metroStop11.setRailwayTrasnferEnable(true);
        metroStop11.setMetroTransferEnable(true);

        MetroStop metroStop12 = new MetroStop("Stadyum", 1);

        MetroStop metroStop13 = new MetroStop("Sanayi", 1);
        MetroStop metroStop14 = new MetroStop("Bölge", 1);
        MetroStop metroStop15 = new MetroStop("Bornova", 1);
        metroStop15.setBusTransferEnable(true);

        MetroStop metroStop16 = new MetroStop("Ege Üniversitesi", 1);
        MetroStop metroStop17 = new MetroStop("Evka 3", 1);
        metroStop17.setBusTransferEnable(true);

        ArrayList<MetroStop> metroLine_one = new ArrayList<>() ;
        metroLine_one.add(metroStop1);
        metroLine_one.add(metroStop2);
        metroLine_one.add(metroStop3);
        metroLine_one.add(metroStop4);
        metroLine_one.add(metroStop5);
        metroLine_one.add(metroStop6);
        metroLine_one.add(metroStop7);
        metroLine_one.add(metroStop8);
        metroLine_one.add(metroStop9);
        metroLine_one.add(metroStop10);
        metroLine_one.add(metroStop11);
        metroLine_one.add(metroStop12);
        metroLine_one.add(metroStop13);
        metroLine_one.add(metroStop14);
        metroLine_one.add(metroStop15);
        metroLine_one.add(metroStop16);
        metroLine_one.add(metroStop17);



        ///METRO HATTI 2 (mavi)
        MetroStop metroStop29 = new MetroStop("Menemen", 2, 3);
        metroStop29.setBusTransferEnable(true);
        metroStop29.setRailwayTrasnferEnable(true);
        metroStop29.setMetroTransferEnable(true);

        MetroStop metroStop18 = new MetroStop("Egekent 2", 2);

        MetroStop metroStop19 = new MetroStop("Ulukent", 2);
        metroStop19.setBusTransferEnable(true);

        MetroStop metroStop20 = new MetroStop("Egekent", 2);
        metroStop20.setBusTransferEnable(true);

        MetroStop metroStop21 = new MetroStop("Ata Sanayi", 2);

        MetroStop metroStop22 = new MetroStop("Çiğli", 2);
        metroStop22.setBusTransferEnable(true);
        metroStop22.setRailwayTrasnferEnable(true);

        MetroStop metroStop23 = new MetroStop("Mavişehir", 2);
        metroStop23.setTramwayTransferEnable(true);
        metroStop23.setBusTransferEnable(true);

        MetroStop metroStop24 = new MetroStop("Şemikler", 2);
        MetroStop metroStop25 = new MetroStop("Demirköprü", 2);
        MetroStop metroStop26 = new MetroStop("Nergiz", 2);
        MetroStop metroStop27 = new MetroStop("Karşıyaka", 2);

        MetroStop metroStop28 = new MetroStop("Alabey", 2);
        metroStop28.setTramwayTransferEnable(true);

        MetroStop metroStop30 = new MetroStop("Naldöken", 2);

        MetroStop metroStop31 = new MetroStop("Turan", 2);
        metroStop31.setBusTransferEnable(true);

        MetroStop metroStop32 = new MetroStop("Bayraklı", 2);
        MetroStop metroStop33 = new MetroStop("Salhane", 2);
        metroStop33.setBusTransferEnable(true);

        MetroStop metroStop34 = new MetroStop("Alsancak", 2);
        MetroStop metroStop36 = new MetroStop("Kemer", 2);
        MetroStop metroStop37 = new MetroStop("Şirinyer", 2);
        MetroStop metroStop38 = new MetroStop("Koşu", 2);
        MetroStop metroStop39 = new MetroStop("İnklap", 2);
        MetroStop metroStop40 = new MetroStop("Semt Garajı", 2);
        MetroStop metroStop41 = new MetroStop("Esbaş", 2);
        MetroStop metroStop42 = new MetroStop("Gaziemir", 2);
        MetroStop metroStop43 = new MetroStop("Sarnıç", 2);
        MetroStop metroStop44 = new MetroStop("Adnan Menderes Havalimanı", 2);
        MetroStop metroStop45 = new MetroStop("Cuma Ovası", 2, 4);

        ArrayList<MetroStop> metroLine_two = new ArrayList<>() ;
        //aktarma istasyonları (krımızı-mavi, mavi-açıkyeşil, mavi-koyuyeşil)
        metroLine_two.add(metroStop10);
        metroLine_two.add(metroStop11);
        metroLine_two.add(metroStop18);
        metroLine_two.add(metroStop19);
        metroLine_two.add(metroStop20);
        metroLine_two.add(metroStop21);
        metroLine_two.add(metroStop22);
        metroLine_two.add(metroStop23);
        metroLine_two.add(metroStop24);
        metroLine_two.add(metroStop25);
        metroLine_two.add(metroStop26);
        metroLine_two.add(metroStop27);
        metroLine_two.add(metroStop28);
        metroLine_two.add(metroStop29);
        metroLine_two.add(metroStop30);
        metroLine_two.add(metroStop31);
        metroLine_two.add(metroStop32);
        metroLine_two.add(metroStop33);
        metroLine_two.add(metroStop34);
        metroLine_two.add(metroStop36);
        metroLine_two.add(metroStop37);
        metroLine_two.add(metroStop38);
        metroLine_two.add(metroStop39);
        metroLine_two.add(metroStop40);
        metroLine_two.add(metroStop41);
        metroLine_two.add(metroStop42);
        metroLine_two.add(metroStop43);
        metroLine_two.add(metroStop44);
        metroLine_two.add(metroStop45);

        ///metroline 3 (açık yeşil)
        MetroStop metroStop46 = new MetroStop("Aliağa", 3);

        MetroStop metroStop47 = new MetroStop("Biçerova", 3);
        metroStop47.setBusTransferEnable(true);

        MetroStop metroStop48 = new MetroStop("Hatundere", 3);
        metroStop48.setBusTransferEnable(true);

        ArrayList<MetroStop> metroLine_three = new ArrayList<>() ;
        metroLine_three.add(metroStop29);
        metroLine_three.add(metroStop46);
        metroLine_three.add(metroStop47);
        metroLine_three.add(metroStop48);

        ///metro line 4 (koyu-yeşil)
        MetroStop metroStop49 = new MetroStop("Develi", 4);

        MetroStop metroStop50 = new MetroStop("Tekeli", 4);
        metroStop50.setBusTransferEnable(true);

        MetroStop metroStop51 = new MetroStop("Pancar", 4);
        metroStop51.setBusTransferEnable(true);
        metroStop51.setRailwayTrasnferEnable(true);

        MetroStop metroStop52 = new MetroStop("Kuşçuburun", 4);
        metroStop52.setBusTransferEnable(true);

        MetroStop metroStop53 = new MetroStop("Torbalı", 4);
        metroStop53.setRailwayTrasnferEnable(true);

        MetroStop metroStop54 = new MetroStop("Tepeköy", 4);
        metroStop54.setBusTransferEnable(true);

        ArrayList<MetroStop> metroLine_four = new ArrayList<>() ;
        metroLine_four.add(metroStop45);
        metroLine_four.add(metroStop49);
        metroLine_four.add(metroStop50);
        metroLine_four.add(metroStop51);
        metroLine_four.add(metroStop52);
        metroLine_four.add(metroStop53);
        metroLine_four.add(metroStop54);

        metroLines.put(1, metroLine_one);
        metroLines.put(2, metroLine_two);
        metroLines.put(3, metroLine_three);
        metroLines.put(4, metroLine_four);

    }
}
