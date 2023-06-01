package com.wcm.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.dto.ReqStationDto;
import com.wcm.dto.ResStationDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Staff;
import com.wcm.model.Station;
import com.wcm.model.User;
import com.wcm.repository.StaffRepository;
import com.wcm.repository.StationRepository;
import com.wcm.repository.UserRepository;
import com.wcm.repository.WheelChairRepository;
import com.wcm.service.SationServiceMAA;
import com.wcm.service.StaffService;
import com.wcm.service.StationCommonService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("api/station")
public class StationController {
	@Autowired
	private StationRepository stationRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ResponseDto responseDto;

	@Autowired
	private ResStationDto resStationDto;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private SationServiceMAA stationService;
	
	@Autowired 
	private StaffRepository staffRepository;
	
	@Autowired
	private WheelChairRepository wheelChairRepository;

	@PostMapping("/add")
	public ResponseEntity<Object> postStation(@RequestBody ReqStationDto stationDto){
		User user = new User();
		user.setUsername(stationDto.getUsername());
		user.setPassword(stationDto.getPassword());
		user.setRole("STATION");
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);

		user = userRepo.save(user); // saving user to attach id to it
		// putting data into station and saving it
		Station station = new Station();
		station.setName(stationDto.getName());
		station.setStNumber(stationDto.getStNumber());
		station.setLocation(stationDto.getLocation());
		station.setType(stationDto.getType());
		station.setUser(user);
		stationRepo.save(station);

		responseDto.setMessage("Sation saved");
		StationCommonService stationService = new StationCommonService(station.getStNumber(), "AVAILABLE", wheelChairRepository, staffRepository, "15000", "0");
		StationCommonService.setStationOIbject(stationService);
		return ResponseEntity.status(HttpStatus.OK)
				.body(responseDto);

	}
	@GetMapping("/start/service/{id}")
	public ResponseEntity<Object> startService(@PathVariable("id") Long id){
		Optional<Station> optional = stationRepo.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Station ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Station station = optional.get();
		try {
			StationCommonService.stationDict.get(station.getStNumber());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		responseDto.setMessage("Service Started");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getStationById(@PathVariable("id") Long id, Principal principal) {

		Optional<Station> optional = stationRepo.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Airline ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Station airline = optional.get();
		resStationDto.setId(airline.getId());
		resStationDto.setName(resStationDto.getName());
		resStationDto.setStNumber(resStationDto.getStNumber());
		resStationDto.setType(resStationDto.getType());
//		resStationDto.setUserId(airline.getUser().getId());
		resStationDto.setUsername(airline.getUser().getUsername());
		resStationDto.setStaff(staffService.getStaff(principal));
		resStationDto.setStaff(resStationDto.getStaff());

		return ResponseEntity.status(HttpStatus.OK).body(resStationDto);
	}
	
	@GetMapping("/get/staff/wc")
	public Staff getStaffFroQueue() {
		Staff staff = (Staff) stationService.getStaff();
		staffService.updateStaffStatus(staff.getId());
		return staff;
	}
}

