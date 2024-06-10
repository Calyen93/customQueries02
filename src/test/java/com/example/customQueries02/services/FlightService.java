package com.example.customQueries02.services;

import com.example.customQueries02.models.Flight;
import com.example.customQueries02.repositories.FlightRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public void provisionFlights(int n) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            Flight flight = new Flight();
            flight.setFromAirport(randomString(random, 5));
            flight.setToAirport(randomString(random, 5));
            flight.setStatus(random.nextBoolean() ? "ONTIME" : "DELAYED");
            flightRepository.save(flight);
        }
    }

    public Page<Flight> getAllFlights(int page, int size) {
        return flightRepository.findAll(PageRequest.of(page, size));
    }

    public List<Flight> getOnTimeFlights() {
        return flightRepository.findAllByStatus("ONTIME");
    }

    public List<Flight> getCustomFlights(String p1, String p2) {
        return flightRepository.findByStatusIn(List.of(p1, p2));
    }

    private String randomString(Random random, int length) {
        String chars = "das1rasda";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
