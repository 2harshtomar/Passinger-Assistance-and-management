package com.wcm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wcm.demo.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	@Query("select s from Staff s where s.user.username=?1")
	Staff findStaffDetails(String username);
}
