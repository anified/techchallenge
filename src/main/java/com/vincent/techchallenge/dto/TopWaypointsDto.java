package com.vincent.techchallenge.dto;

import java.util.ArrayList;

import com.vincent.techchallenge.model.WaypointCount;

public class TopWaypointsDto {
    private String airport;
    private ArrayList<WaypointCount> topWaypoints;

    public String getAirport() {
        return airport;
    }

    public void setAirport(String value) {
        this.airport = value;
    }

    public ArrayList<WaypointCount> getTopWaypoints() {
        return topWaypoints;
    }

    public void setTopWaypoints(ArrayList<WaypointCount> topWaypoints2) {
        this.topWaypoints = topWaypoints2;
    }
}