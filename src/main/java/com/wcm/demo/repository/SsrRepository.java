package com.wcm.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

import com.wcm.demo.enums.SsrStatusEnum;
import com.wcm.demo.model.Ssr;

public interface SsrRepository extends JpaRepository<Ssr, Long>{

	Optional<Ssr> FindByStatus(SsrStatusEnum complete);

	Ssr deleteByStatus();
	
=======
import com.wcm.demo.model.Ssr;

public interface SsrRepository extends JpaRepository<Ssr, Long>{
//	@Query("select s from Ssr s where s.staff.status = ?1")
//	Ssr FindByStatus();
>>>>>>> 2aba7b7f5da9a8635552d53ea49e6655441f482c

}
