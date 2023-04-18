package com.wcm.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wcm.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	@Query("select s from Staff s where s.user.username=?1")
	Staff findStaffDetails(String username);
	// returns a List
	@Query("select s from Staff s where s.staffCode=?1")
	List<Staff> findstaffByCode(String code);
	
	// returns a set with no duplicates
	@Query("select s from Staff s where s.staffCode=?1 AND s.status=?2")
	Set<Object> getStaffSet(String code, String status);
	
	
}
