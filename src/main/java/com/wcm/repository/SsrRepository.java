package com.wcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wcm.model.Ssr;


public interface SsrRepository extends JpaRepository<Ssr, Long> {
	@Query("select s from Ssr s where s.status <> 'ARCHIVED'")
	List<Ssr> getSsrOnStaff(Long id);
}
