package com.example.monster.welcometoizmir.classes;

import com.example.monster.welcometoizmir.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MONSTER on 19.11.2016.
 */

public class BusStop implements Serializable {
    private String id;
    private String name;
    private int iconId;

    private boolean isMetroTransferEnable;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isMetroTransferEnable() {
        return isMetroTransferEnable;
    }

    public void setMetroTransferEnable(boolean metroTransferEnable) {
        isMetroTransferEnable = metroTransferEnable;
    }


}
