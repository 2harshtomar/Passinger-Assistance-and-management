package com.wcm.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.demo.dto.ResSsrDto;
import com.wcm.demo.model.Ssr;
import com.wcm.demo.services.SsrService;

@RestController
@RequestMapping("/api/ssr")
public class SsrController {
	
	@Autowired
	private SsrService ssrService;
	
	@PostMapping("/add")
	public ResponseEntity<Object> addSsr(@RequestBody Ssr ssr) {
		return ssrService.addSsr(ssr);
	}
	
	@GetMapping("/all")
	public List<ResSsrDto> getallssr(){
		return ssrService.getallssr();
	}
	
}
