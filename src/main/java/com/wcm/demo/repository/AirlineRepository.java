package com.wcm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wcm.demo.model.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
	@Query("select a from Airline a where a.user.username=?1")
	Airline findAirlineDetails(String username);
}
