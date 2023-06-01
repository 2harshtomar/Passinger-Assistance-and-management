package com.wcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wcm.model.Carrier;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
	@Query("select c from Carrier c where c.airline.id=?1")
	List<Carrier> getAllCarriers(Long aid);
}
