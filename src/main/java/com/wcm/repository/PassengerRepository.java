package com.wcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcm.model.PassengerDetails;

public interface PassengerRepository extends JpaRepository<PassengerDetails, Long> {
	
}
