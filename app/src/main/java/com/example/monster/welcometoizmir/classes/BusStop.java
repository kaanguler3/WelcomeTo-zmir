package com.example.monster.welcometoizmir.classes;

import com.example.monster.welcometoizmir.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MONSTER on 19.11.2016.
 */

public class BusStop extends Stop implements Serializable {
    ArrayList<Bus> buses;



    public BusStop(){
        setIconId(R.drawable.ic_bus);
        buses = new ArrayList<>();
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public void setBuses(ArrayList<Bus> buses) {
        this.buses = buses;
    }
}
