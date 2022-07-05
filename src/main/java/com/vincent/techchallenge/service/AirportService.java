package com.vincent.techchallenge.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vincent.techchallenge.dto.AirportDto;
import com.vincent.techchallenge.dto.SIDSTARDto;
import com.vincent.techchallenge.dto.TopWaypointsDto;
import com.vincent.techchallenge.dto.WaypointDto;
import com.vincent.techchallenge.model.WaypointCount;

@Service
public class AirportService {
    @Value("${ATMS_URL}")
    private String url;

    @Value("${ATMS_HEADER_KEY}")
    private String headerName;

    @Value("${ATMS_HEADER_VALUE}")
    private String headerValue;

    private HttpEntity<String> _getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(headerName, headerValue);

        HttpEntity<String> entity = new HttpEntity<String>("body", headers);

        return entity;
    }

    public AirportDto[] getAirports() {
        String url = this.url + "airac/airports";
        System.out.println("Calling " + url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AirportDto[]> response = restTemplate.exchange(url, HttpMethod.GET, this._getHttpEntity(), AirportDto[].class);
        AirportDto[] result = response.getBody();

        return result;
    }

    public SIDSTARDto[] getStandardInstrumentDepartureByIcao(String icao) {
        String url = this.url + "airac/sids/airport/" + icao;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SIDSTARDto[]> response = restTemplate.exchange(url, HttpMethod.GET, this._getHttpEntity(), SIDSTARDto[].class);
        SIDSTARDto[] result = response.getBody();

        return result;
    }

    public SIDSTARDto[] getStandardTerminalArrivalRouteByIcao(String icao) {
        String url = this.url + "airac/stars/airport/" + icao;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SIDSTARDto[]> response = restTemplate.exchange(url, HttpMethod.GET, this._getHttpEntity(), SIDSTARDto[].class);
        SIDSTARDto[] result = response.getBody();

        return result;
    }

    public TopWaypointsDto getTopTwoWaypoints(String icao, SIDSTARDto[] SIDs) {
        TopWaypointsDto result = new TopWaypointsDto();
        // Return airport icao
        result.setAirport(icao);
        
        // Count waypoints
        HashMap<String, WaypointDto> waypointMap = new HashMap<String, WaypointDto>();
        HashMap<String, Integer> waypointCountMap = new HashMap<String, Integer>();
        for (SIDSTARDto SID : SIDs) {
            for (WaypointDto waypoint : SID.getWaypoints()) {
                String uid = waypoint.getUid();
                if (waypointMap.containsKey(uid)) {
                    int count = waypointCountMap.get(uid);
                    waypointCountMap.put(uid, count + 1);
                } else {
                    waypointMap.put(uid, waypoint);
                    waypointCountMap.put(uid, 1);
                }
            }
        }

        // Get the top waypoints
        ArrayList<WaypointCount> topWaypoints = this._getTopWaypoints(waypointMap, waypointCountMap);
        result.setTopWaypoints(topWaypoints);

        return result;
    }

    // Can consider sorting the map instead if returning more waypoints
    private ArrayList<WaypointCount> _getTopWaypoints(HashMap<String, WaypointDto> waypointMap, HashMap<String, Integer> waypointCountMap) {
        WaypointCount firstWaypoint = new WaypointCount();
        WaypointCount secondWaypoint = new WaypointCount();
        firstWaypoint.setCount(0);
        secondWaypoint.setCount(0);

        for (HashMap.Entry<String, Integer> entry : waypointCountMap.entrySet()) {
            WaypointCount waypoint = new WaypointCount();
            String key = entry.getKey();
            int value = entry.getValue();
            String name = waypointMap.get(key).getName();
            if (value > firstWaypoint.getCount()) {
                secondWaypoint = firstWaypoint;
                waypoint.setName(name);
                waypoint.setCount(value);
                firstWaypoint = waypoint;
            } else if (value > secondWaypoint.getCount()) {
                waypoint.setName(name);
                waypoint.setCount(value);
                secondWaypoint = waypoint;
            }
        }

        ArrayList<WaypointCount> topWaypoints = new ArrayList<WaypointCount>();
        if (firstWaypoint.getName() != null) {
            topWaypoints.add(firstWaypoint);
        }
        if (secondWaypoint.getName() != null) {
            topWaypoints.add(secondWaypoint);
        }

        return topWaypoints;
    }
}
