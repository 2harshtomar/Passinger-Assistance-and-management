package com.wcm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.dto.ResAirlineDto;
import com.wcm.dto.ResStaffDto;
import com.wcm.dto.ResStationDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Airline;
import com.wcm.model.Staff;
import com.wcm.model.Station;
import com.wcm.model.User;
import com.wcm.repository.AirlineRepository;
import com.wcm.repository.StaffRepository;
import com.wcm.repository.StationRepository;
import com.wcm.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
		User user = userRepo.findByUsername(username);
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
		User user = userRepo.findByUsername(username);
		if(user == null) {
			responseDto.setMessage("Invalid Credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
 		}
		if(user.getRole().equalsIgnoreCase("STAFF")) {
			Staff staff = staffRepo.findStaffDetails(username);
			staffDto.setId(staff.getId());
			staffDto.setName(staff.getName());
			staffDto.setStaffCode(staff.getStaffCode());
			staffDto.setStatus(staff.getStatus());
			staffDto.setUsername(staff.getUser().getUsername());
			staffDto.setContact(staff.getContact());
			staffDto.setEmail(staff.getEmail());

			return staffDto;
		}

		if(user.getRole().equalsIgnoreCase("STATION")) {
			Station station = stationRepo.findStationDetails(username);
			stationDto.setId(station.getId());
			stationDto.setName(station.getName());
			stationDto.setLocation(station.getLocation());
			stationDto.setStNumber(station.getStNumber());
			stationDto.setUsername(station.getUser().getUsername());
			stationDto.setUserId(station.getUser().getId());

			return stationDto;
		}

		if(user.getRole().equalsIgnoreCase("AIRLINE")) {
			Airline airline = airlineRepo.findAirlineDetails(username);
			airlineDto.setId(airline.getId());
			airlineDto.setName(airline.getName());
			airlineDto.setAirlineCode(airline.getAirlineCode());
			airlineDto.setUsername(airline.getUser().getUsername());
			airlineDto.setUserId(airline.getUser().getId());
		}
		return null;
	}

}

