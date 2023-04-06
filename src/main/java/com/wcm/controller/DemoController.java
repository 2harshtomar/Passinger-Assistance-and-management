package com.wcm.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.ApplicationShutDownManager;
import com.wcm.dto.ResponseDto;
import com.wcm.exception.ResourseNotFoundException;
import com.wcm.model.Staff;
import com.wcm.service.SationService;
import com.wcm.service.StaffService;
import com.wcm.service.WheelChairService;

@RestController
@RequestMapping("/api/demo")
public class DemoController {
	
//	@Autowired
//	private WheelChairRepository wcRepo;
	
	@Autowired
	private WheelChairService wcService;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private SationService stationService;
	
	@Autowired
	private ApplicationShutDownManager shutDownManager;
	
	
	@PutMapping("/update/status/{id}")
	public ResponseEntity<Object> UpdateStatus(@PathVariable("id") Long id){
		try {
			wcService.UpdateStatus(id);
			responseDto.setMessage("Status Updated");
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} catch (ResourseNotFoundException e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}
	@GetMapping("/get/staff/based/on/code")
	public List<Staff> getStaffBasedOnCode(Principal principal){
		return staffService.getStaffByCode(principal);
	}
	@GetMapping("/invoke")
	public void demo() {
		stationService.addElement();
		stationService.displayQueue();
	}
	@GetMapping("/shutdown")
	public void shutDown() {
		stationService.DeleteQueue();
		shutDownManager.initiateShutdown(0);
		System.out.println("Terminated");
	}
}
