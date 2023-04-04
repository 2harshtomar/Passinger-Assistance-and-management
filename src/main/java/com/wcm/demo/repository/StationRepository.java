package com.wcm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wcm.demo.model.Station;

public interface StationRepository extends JpaRepository<Station, Long> {
	@Query("select s from Station s where s.user.username=?1")
	Station findStationDetails(String username);
}
