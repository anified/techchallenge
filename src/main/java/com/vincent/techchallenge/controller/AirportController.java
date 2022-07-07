package com.vincent.techchallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vincent.techchallenge.dto.AirportDto;
import com.vincent.techchallenge.dto.SIDSTARDto;
import com.vincent.techchallenge.dto.TopWaypointsDto;
import com.vincent.techchallenge.service.AirportService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/techchallenge/v1")
public class AirportController {
    @Autowired
    AirportService airportService;

    @GetMapping(path = "/airports", produces = "application/json")
    public ResponseEntity<AirportDto[]> getAirports() {
        AirportDto[] airports = airportService.getAirports();
        return new ResponseEntity<AirportDto[]>(airports, HttpStatus.OK);
    }

    @GetMapping(path = "/getSIDTopWaypoints/{icao}", produces = "application/json")
    public ResponseEntity<TopWaypointsDto> getSIDTopWaypoints(@PathVariable String icao) {
        SIDSTARDto[] SIDs = airportService.getStandardInstrumentDepartureByIcao(icao);
        TopWaypointsDto topTwoWaypoints = airportService.getTopTwoWaypoints(icao, SIDs);

        return new ResponseEntity<TopWaypointsDto>(topTwoWaypoints, HttpStatus.OK);
    }

    @GetMapping(path = "/getSTARTopWaypoints/{icao}", produces = "application/json")
    public ResponseEntity<TopWaypointsDto> getSTARTopWaypoints(@PathVariable String icao) {
        SIDSTARDto[] SIDs = airportService.getStandardTerminalArrivalRouteByIcao(icao);
        TopWaypointsDto topTwoWaypoints = airportService.getTopTwoWaypoints(icao, SIDs);

        return new ResponseEntity<TopWaypointsDto>(topTwoWaypoints, HttpStatus.OK);
    }
}
