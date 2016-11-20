package com.example.monster.welcometoizmir.classes;

import com.example.monster.welcometoizmir.R;

import java.util.ArrayList;

/**
 * Created by MONSTER on 19.11.2016.
 */

public class MetroStop{
    private String name;
    private int iconId;
    private int lineId, lineId2;
    private int lineColorID1, lineColorID2;
    private boolean isMetroTransferEnable; // bu istasyondan başka metroya aktarma yapılıyor mu
    private boolean isBusTransferEnable; /// bu istasyondan otobüs geçiyor mu.
    private boolean isFerryTransferEnable;
    private boolean isTramwayTransferEnable;
    private boolean isRailwayTrasnferEnable;

    public MetroStop(String name, int lineId){
        setName(name);
        setLineId(lineId);
        setIconId(R.drawable.ic_metro);
    }

    public MetroStop(String name, int lineId, int lineId2){
        setName(name);
        setLineId(lineId);
        setLineId2(lineId2);
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {

        switch (lineId){
            case 1:
                lineColorID1= R.color.red;
                break;
            case 2:
                lineColorID1 = R.color.blue;
                break;
            case 3:
                lineColorID1 = R.color.light_green;
                break;
            case 4:
                lineColorID1 = R.color.dark_green;
                break;
        }
        this.lineId = lineId;
    }

    public int getLineId2() {
        return lineId2;
    }

    public void setLineId2(int lineId2) {
        this.lineId2 = lineId2;
        switch (lineId2){
            case 1:
                lineColorID2 = R.color.red;
                break;
            case 2:
                lineColorID2 = R.color.blue;
                break;
            case 3:
                lineColorID2 = R.color.light_green;
                break;
            case 4:
                lineColorID2 = R.color.dark_green;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLineColorID1() {
        return lineColorID1;
    }

    public void setLineColorID1(int lineColorID1) {
        this.lineColorID1 = lineColorID1;
    }

    public int getLineColorID2() {
        return lineColorID2;
    }

    public void setLineColorID2(int lineColorID2) {
        this.lineColorID2 = lineColorID2;
    }

    public boolean isRailwayTrasnferEnable() {
        return isRailwayTrasnferEnable;
    }

    public void setRailwayTrasnferEnable(boolean railwayTrasnferEnable) {
        isRailwayTrasnferEnable = railwayTrasnferEnable;
    }

    public boolean isTramwayTransferEnable() {
        return isTramwayTransferEnable;
    }

    public void setTramwayTransferEnable(boolean tramwayTransferEnable) {
        isTramwayTransferEnable = tramwayTransferEnable;
    }

    public boolean isFerryTransferEnable() {
        return isFerryTransferEnable;
    }

    public void setFerryTransferEnable(boolean ferryTransferEnable) {
        isFerryTransferEnable = ferryTransferEnable;
    }

    public boolean isBusTransferEnable() {
        return isBusTransferEnable;
    }

    public void setBusTransferEnable(boolean busTransferEnable) {
        isBusTransferEnable = busTransferEnable;
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
}
