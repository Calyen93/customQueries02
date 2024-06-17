package com.example.customQueries02.controllers;

import com.example.customQueries02.models.Flight;
import com.example.customQueries02.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/provision")
    public void provisionFlights(@RequestParam(required = false, defaultValue = "100") int n) {
        flightService.provisionFlights(n);
    }

    @GetMapping("/all")
    public Page<Flight> getAllFlights(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        return flightService.getAllFlights(page, size);
    }

    @GetMapping("/ontime")
    public List<Flight> getOnTimeFlights() {
        return flightService.getOnTimeFlights();
    }

    @GetMapping("/custom")
    public List<Flight> getCustomFlights(@RequestParam String p1, @RequestParam String p2) {
        return flightService.getCustomFlights(p1, p2);
    }
}
