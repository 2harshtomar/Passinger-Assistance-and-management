package com.wcm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcm.demo.model.Station;

public interface StationRepository extends JpaRepository<Station, Long> {

}
