package com.wcm.controller;

import java.security.Principal;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.dto.ResWheelchairDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Wheel_Chair;
import com.wcm.repository.WheelChairRepository;
import com.wcm.service.StationRouterService;
import com.wcm.service.WheelChairService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/wheelChair")
public class WheelchairController {
	
	@Autowired
	ResWheelchairDto resWheelchairDto;
	
	@Autowired
	WheelChairRepository wcr;
	
	@Autowired
	WheelChairService wheelChairService;
	
	@Autowired
	StationRouterService srs;
	
	/* Author : Aaditya Mohan
	 * emp id : 2000081375
	 */
	//get
	
	@GetMapping("/get/wheelChair")
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
	
	
	@PostMapping("/add/{userID}")
	public ResponseEntity<Object> addWheelchair(@RequestBody ResponseDto responseDto,@PathVariable ("userID") Long id){
		
		Wheel_Chair wheelchair = new Wheel_Chair();
//		wheelchair.setWcCode(srs.getEntity(id));
		wheelchair.setWcStatus(true);
		wheelchair = wcr.save(wheelchair);
		String code = srs.getEntity(id)+ "-"+wheelchair.getId();
		wheelchair.setWcCode(code);
		wcr.save(wheelchair);
		
		responseDto.setMessage("Wheelchair added sucessfully.");
		return ResponseEntity.status(HttpStatus.OK)
				.body(responseDto);
	}
	
	@GetMapping("/get/all")
	public List<Wheel_Chair> getWheelChairs(Principal principal){
		return wheelChairService.getStaff(principal);
	}

}
