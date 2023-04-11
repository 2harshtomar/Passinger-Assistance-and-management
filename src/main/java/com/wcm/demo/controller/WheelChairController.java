package com.wcm.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.demo.dto.WheelChairResponseDto;
import com.wcm.demo.model.Wheel_Chair;
import com.wcm.demo.services.WheelChairService;

@RestController
@RequestMapping("/wheelchair")
public class WheelChairController {
	
	@GetMapping("/show")
	public List<WheelChairResponseDto> getAllWheelChair(){
		return WheelChairService.getAllWheelChair();
	}
	
	@PostMapping("/add/{wcCode}")
	public ResponseEntity<String> AddWheelChair(Wheel_Chair wc, @PathVariable("wcCode")String wcCode){
		return WheelChairService.AddAllWheelChair(wc, wcCode);
	}
	
	
}
