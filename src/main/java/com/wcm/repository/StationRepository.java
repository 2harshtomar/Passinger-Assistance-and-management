package com.wcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wcm.model.Station;

public interface StationRepository extends JpaRepository<Station, Long> {
	@Query("select s from Station s where s.user.username=?1")
	Station findStationDetails(String username);

	@Query("select s.stNumber from Station s where s.user.username=?1")
	String GetStationCode(String username);
	
	@Query("select s from Station s where s.user.id=?1")
	Station getStationByUserId(Long id);
}
