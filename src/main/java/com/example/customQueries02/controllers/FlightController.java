package com.example.customQueries02.controllers;

import com.example.customQueries02.models.Flight;
import com.example.customQueries02.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/provision")
    public void provisionFlights(@RequestParam(value = "n", defaultValue = "100") int n) {
        flightService.provisionFlights(n);
    }

    @GetMapping
    public Page<Flight> getAllFlights(Pageable pageable) {
        return flightService.getAllFlights(pageable);
    }

    @GetMapping("/ontime")
    public List<Flight> getOntimeFlights() {
        return flightService.getOntimeFlights();
    }

    @GetMapping("/status")
    public List<Flight> getFlightsByStatus(@RequestParam String p1, @RequestParam String p2) {
        return flightService.getFlightsByStatus(p1, p2);
    }
}
