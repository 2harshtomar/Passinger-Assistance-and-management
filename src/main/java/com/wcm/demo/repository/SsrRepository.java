package com.wcm.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wcm.demo.model.Ssr;

public interface SsrRepository extends JpaRepository<Ssr, Long>{
//	@Query("select s from Ssr s where s.staff.status = ?1")
//	Ssr FindByStatus();

}
