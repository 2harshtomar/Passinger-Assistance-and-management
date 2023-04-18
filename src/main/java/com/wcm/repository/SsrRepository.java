package com.wcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wcm.model.Ssr;


public interface SsrRepository extends JpaRepository<Ssr, Long> {
	@Query("select s from Ssr s where (s.status = 'ACTIVE') and s.sStaff.id = ?1 or s.dStaff.id = ?1")
	Ssr getSsrOnStaff(Long id);
}
