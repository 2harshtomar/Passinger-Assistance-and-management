package com.wcm.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.demo.dto.SsrResponseDto;
import com.wcm.demo.model.Ssr;
import com.wcm.demo.services.SsrService;

@RestController
public class SsrController {
	
	@Autowired
	private SsrService ssrService;
	
	@PostMapping("/add")
	public Ssr addSsr(@RequestBody Ssr ssr) {
		return ssrService.addSsr(ssr);
	}
	
	@GetMapping("/all/ssr")
	public List<SsrResponseDto> getallssr(){
		return ssrService.getallssr();
	}

}
