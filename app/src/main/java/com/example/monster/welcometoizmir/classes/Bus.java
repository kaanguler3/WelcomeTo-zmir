package com.example.monster.welcometoizmir.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by MONSTER on 8.11.2016.
 */

public class Bus implements Serializable {
    int integerId;
    String busId;
    ArrayList<BusStop> stops;
    HashMap<String, ArrayList<String>> firstStopMoveHours; ///"hafta içi" [06:40, 07:10, ....]
    HashMap<String, ArrayList<String>> lastStopMoveHours;
    public Bus(){
        stops = new ArrayList<>();
        firstStopMoveHours = new HashMap<>();
        lastStopMoveHours = new HashMap<>();
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.integerId = Integer.valueOf(busId);
        this.busId = busId;
    }

    public ArrayList<BusStop> getStops() {
        return stops;
    }

    public void setStops(ArrayList<BusStop> stops) {
        this.stops = stops;
    }
    public HashMap<String, ArrayList<String>> getFirstStopMoveHours() {
        return firstStopMoveHours;
    }

    public void setFirstStopMoveHours(HashMap<String, ArrayList<String>> firstStopMoveHours) {
        this.firstStopMoveHours = firstStopMoveHours;
    }

    public HashMap<String, ArrayList<String>> getLastStopMoveHours() {
        return lastStopMoveHours;
    }

    public void setLastStopMoveHours(HashMap<String, ArrayList<String>> lastStopMoveHours) {
        this.lastStopMoveHours = lastStopMoveHours;
    }

    public int getIntegerId() {
        return integerId;
    }

    public void setIntegerId(int integerId) {
        this.integerId = integerId;
    }
}
