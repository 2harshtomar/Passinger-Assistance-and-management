package com.wcm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcm.demo.model.Ssr;

public interface SsrRepository extends JpaRepository<Ssr, Long>{
	

}
