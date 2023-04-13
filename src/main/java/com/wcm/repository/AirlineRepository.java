package com.wcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wcm.model.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
	@Query("select a from Airline a where a.user.username=?1")
	Airline findAirlineDetails(String username);

	@Query("select a.airlineCode from Airline a where a.user.username=?1")
	String getAirlineCode(String username);
	
	@Query("select a from Airline a where a.user.id=?1")
	Airline getAirlineByUserId(Long id);
}
