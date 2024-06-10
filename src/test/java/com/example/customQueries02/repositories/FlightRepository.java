package com.example.customQueries02.repositories;

import com.example.customQueries02.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAllByStatus(String status);
    List<Flight> findByStatusIn(List<String> statuses);
}
