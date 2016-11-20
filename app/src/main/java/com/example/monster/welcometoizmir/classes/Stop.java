package com.example.monster.welcometoizmir.classes;

import java.util.ArrayList;

/**
 * Created by MONSTER on 19.11.2016.
 */

public class Stop {
    private String id;
    private String name;
    private ArrayList<Integer> iconIDs;
    private int iconId;

    private boolean isMetroTransferEnable; // bu istasyondan başka metroya aktarma yapılıyor mu
    private boolean isBusTransferEnable; /// bu istasyondan otobüs geçiyor mu.
    private boolean isFerryTransferEnable;
    private boolean isTramwayTransferEnable;
    private boolean isRailwayTrasnferEnable;

    public Stop(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isRailwayTrasnferEnable() {
        return isRailwayTrasnferEnable;
    }

    public void setRailwayTrasnferEnable(boolean railwayTrasnferEnable) {
        isRailwayTrasnferEnable = railwayTrasnferEnable;
    }

    public boolean isFerryTransferEnable() {
        return isFerryTransferEnable;
    }

    public void setFerryTransferEnable(boolean ferryTransferEnable) {
        isFerryTransferEnable = ferryTransferEnable;
    }

    public boolean isMetroTransferEnable() {
        return isMetroTransferEnable;
    }

    public void setMetroTransferEnable(boolean metroTransferEnable) {
        isMetroTransferEnable = metroTransferEnable;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public ArrayList<Integer> getIconIDs() {
        return iconIDs;
    }

    public void setIconIDs(ArrayList<Integer> iconIDs) {
        this.iconIDs = iconIDs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTramwayTransferEnable() {
        return isTramwayTransferEnable;
    }

    public void setTramwayTransferEnable(boolean tramwayTransferEnable) {
        isTramwayTransferEnable = tramwayTransferEnable;
    }

    public boolean isBusTransferEnable() {
        return isBusTransferEnable;
    }

    public void setBusTransferEnable(boolean busTransferEnable) {
        isBusTransferEnable = busTransferEnable;
    }
}
