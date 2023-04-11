package com.wcm.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wcm.demo.enums.SsrStatusEnum;
import com.wcm.demo.model.Ssr;

public interface SsrRepository extends JpaRepository<Ssr, Long>{

	Optional<Ssr> FindByStatus(SsrStatusEnum complete);

	Ssr deleteByStatus();
	

}
