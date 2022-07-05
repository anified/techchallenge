package com.vincent.techchallenge.dto;

public class SIDSTARDto {
    private String name;
    private AirportDto airport;
    private WaypointDto[] waypoints;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public AirportDto getAirport() {
        return airport;
    }

    public void setAirport(AirportDto value) {
        this.airport = value;
    }

    public WaypointDto[] getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(WaypointDto[] value) {
        this.waypoints = value;
    }
}
