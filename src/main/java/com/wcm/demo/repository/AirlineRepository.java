package com.wcm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcm.demo.model.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {

}
