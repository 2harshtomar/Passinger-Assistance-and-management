package com.wcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wcm.model.PassengerNameRecord;

public interface PnrRepository extends JpaRepository<PassengerNameRecord, Long> {
	
	@Query("select p from PassengerNameRecord p where pnrNo=?1")
	PassengerNameRecord getPNR(String pnr);

}
