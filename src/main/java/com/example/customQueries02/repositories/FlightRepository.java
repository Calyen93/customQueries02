package com.example.customQueries02.repositories;

import com.example.customQueries02.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAllByStatus(String status);
    @Query("SELECT f FROM Flight f WHERE f.status = :status1 OR f.status = :status2")
    List<Flight> findByStatusIn(List<String> statuses);
}
