package com.wcm.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.demo.dto.ResAirlineDto;
import com.wcm.demo.dto.ResStaffDto;
import com.wcm.demo.dto.ResStationDto;
import com.wcm.demo.dto.ResponseDto;
import com.wcm.demo.model.Airline;
import com.wcm.demo.model.Staff;
import com.wcm.demo.model.Station;
import com.wcm.demo.model.User;
import com.wcm.demo.repository.AirlineRepository;
import com.wcm.demo.repository.StaffRepository;
import com.wcm.demo.repository.StationRepository;
import com.wcm.demo.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Autowired
	private StaffRepository staffRepo;
	
	@Autowired
	private StationRepository stationRepo;
	
	@Autowired
	private AirlineRepository airlineRepo;
	
	@Autowired
	private ResStaffDto staffDto;
	
	@Autowired
	private ResStationDto stationDto;
	
	@Autowired
	private ResAirlineDto airlineDto;
	
	@GetMapping("/login")
	public Object userLogin(Principal principal) {
		String username = principal.getName();
		User user = userRepo.getUserByusername(username);
		if(user == null) {
			responseDto.setMessage("Invalid Credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
 		}
		user.setPassword("Hidden");
		return user;	
	}
	@GetMapping("/get/details")
	public Object getUserDetails(Principal principal) {
		String username = principal.getName();
		User user = userRepo.getUserByusername(username);
		if(user == null) {
			responseDto.setMessage("Invalid Credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
 		}
		if(user.getRole().equalsIgnoreCase("STAFF")) {
			Staff staff = staffRepo.findStaffDetails(username);
			staffDto.setName(staff.getName());
			staffDto.setStaffCode(staff.getStaffCode());
			staffDto.setStatus(staff.getStatus());
			staffDto.setUsername(staff.getUser().getUsername());
			
			return staffDto;
		}
		
		if(user.getRole().equalsIgnoreCase("STATION")) {
			Station station = stationRepo.findStationDetails(username);
			stationDto.setName(station.getName());
			stationDto.setLocation(station.getLocation());
			stationDto.setStNumber(station.getStNumber());
			stationDto.setUsername(station.getUser().getUsername());
			
			return stationDto;
		}
		
		if(user.getRole().equalsIgnoreCase("AIRLINE")) {
			Airline airline = airlineRepo.findAirlineDetails(username);
			airlineDto.setName(airline.getName());
			airlineDto.setAirlineCode(airline.getAirlineCode());
			airlineDto.setUsername(airline.getUser().getUsername());
		}
		return null;
	}

}

