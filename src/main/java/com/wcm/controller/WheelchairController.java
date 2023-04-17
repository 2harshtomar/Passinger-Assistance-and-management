package com.wcm.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wcm.dto.ResWheelchairDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Wheel_Chair;
import com.wcm.repository.WheelChairRepository;


public class WheelchairController {
	
	@Autowired
	ResWheelchairDto resWheelchairDto;
	
	@Autowired
	WheelChairRepository wcr;
	
	//get
	
	@GetMapping("/get/all/wheelchair")
	public ResponseEntity<Object> getWheelchairDetails(@RequestBody Wheel_Chair wheelchair){
		List<Wheel_Chair>list =  wcr.findAll();
		List<ResWheelchairDto> listDto = new ArrayList<>();
		
		for(Wheel_Chair w : list) {
			ResWheelchairDto dto = new ResWheelchairDto();
			dto.setId(w.getId());
			dto.setWcCode(w.getWcCode());
			dto.setWcStatus(w.getWcStatus());
			
			listDto.add(dto);
		}
		
		
		return ResponseEntity.status(HttpStatus.OK).body(listDto);
	}
	
	
	@PostMapping("/add/wheelchair/{code}")
	public ResponseEntity<Object> addWheelchair(@RequestBody ResponseDto responseDto,@PathVariable ("code") String code){
		
		Wheel_Chair wheelchair = new Wheel_Chair();
		wheelchair.setWcCode(code);
		wheelchair.setWcStatus(true);
		wcr.save(wheelchair);
		
		responseDto.setMessage("Wheelchair added sucessfully.");
		return ResponseEntity.status(HttpStatus.OK)
				.body(responseDto);
	}

}
