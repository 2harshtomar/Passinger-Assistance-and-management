package com.wcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wcm.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	@Query("select s from Staff s where s.user.username=?1")
	Staff findStaffDetails(String username);
	@Query("select s from Staff s where s.staffCode=?1")
	List<Staff> findstaffByCode(String code);
}
