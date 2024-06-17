package com.example.customQueries02.repositories;

import com.example.customQueries02.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByStatus(String status);

    @Query("SELECT f FROM Flight AS f WHERE f.status = :p1 OR f.status = :p2")
    List<Flight> findByStatusIn(String p1, String p2);
}