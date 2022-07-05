package com.vincent.techchallenge.dto;

public class WaypointDto {
    private String uid;
    private String name;
    private float lat;
    private float lng;

    public String getUid() {
        return uid;
    }

    public void setUid(String value) {
        this.uid = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float value) {
        this.lat = value;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float value) {
        this.lng = value;
    }
}
