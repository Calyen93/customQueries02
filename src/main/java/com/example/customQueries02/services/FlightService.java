package com.example.customQueries02.services;

import com.example.customQueries02.models.Flight;
import com.example.customQueries02.repositories.FlightRepository;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    private final Random random = new Random();

    public void provisionFlights(int n) {
        List<Flight> flights = IntStream.range(0, n)
                .mapToObj(i -> createRandomFlight())
                .collect(Collectors.toList());
        flightRepository.saveAll(flights);
    }

    private Flight createRandomFlight() {
        Flight flight = new Flight();
        flight.setFromAirport(generateRandomString());
        flight.setToAirport(generateRandomString());
        flight.setStatus(generateRandomStatus());
        return flight;
    }

    private String generateRandomString() {
        return random.ints(65, 90)
                .limit(3)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private String generateRandomStatus() {
        String[] statuses = {"ONTIME", "DELAYED", "CANCELLED"};
        return statuses[random.nextInt(statuses.length)];
    }

    public Page<Flight> getAllFlights(Pageable pageable) {
        return flightRepository.findAll(pageable);
    }

    public List<Flight> getOntimeFlights() {
        return flightRepository.findByStatus("ONTIME");
    }

    public List<Flight> getFlightsByStatus(String p1, String p2) {
        return flightRepository.findByStatusIn(p1, p2);
    }
}
