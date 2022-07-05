package com.vincent.techchallenge.dto;

public class AirportDto {
    private String uid;
    private String name;
    private String icao;
    private float lat;
    private float lng;
    private int alt;
    private String iata;

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

    public String getIcao() {
        return icao;
    }

    public void setIcao(String value) {
        this.icao = value;
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

    public int getAlt() {
        return alt;
    }

    public void setAlt(int value) {
        this.alt = value;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String value) {
        this.iata = value;
    }
}
