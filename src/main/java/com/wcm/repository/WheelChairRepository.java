package com.wcm.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wcm.model.Wheel_Chair;

public interface WheelChairRepository extends JpaRepository<Wheel_Chair, Long> {
	@Query("select w from Wheel_Chair w where w.wcCode LIKE ?1% AND w.wcStatus=?2")
	Set<Object> getWheelChairSet(String code, boolean status);
	
	@Query("select w from Wheel_Chair w where w.wcCode LIKE ?1%")
	List<Wheel_Chair> getWheelChairs(String code);
}
