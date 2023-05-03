package com.wcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcm.model.PassengerNameRecord;

public interface PnrRepository extends JpaRepository<PassengerNameRecord, Long> {

}
