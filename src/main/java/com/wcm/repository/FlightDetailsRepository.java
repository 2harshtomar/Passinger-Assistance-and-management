package com.wcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcm.model.Flight_details;

public interface FlightDetailsRepository extends JpaRepository<Flight_details, Long>{

}
