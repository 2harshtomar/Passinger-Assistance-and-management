package com.wcm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcm.demo.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long>{

}
