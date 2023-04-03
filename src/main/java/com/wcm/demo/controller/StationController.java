package com.wcm.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.demo.dto.ResponseDto;
import com.wcm.demo.dto.ReqStationDto;
import com.wcm.demo.dto.ResStationDto;
import com.wcm.demo.model.Station;
import com.wcm.demo.model.User;
import com.wcm.demo.repository.StationRepository;
import com.wcm.demo.repository.UserRepository;

@RestController
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
	
	@PostMapping("add/station")
	public ResponseEntity<Object> postStation(@RequestBody ReqStationDto stationDto){
		User user = new User();
		user.setUsername(stationDto.getUsername());
		user.setPassword(stationDto.getPassword());
		user.setRole("STATION");
		// password encoder will be here
		
		user = userRepo.save(user); // saving user to attach id to it
		// putting data into station and saving it
		Station station = new Station();
		station.setName(stationDto.getName());
		station.setApNumber(stationDto.getStNumber());
		station.setLocation(stationDto.getLocation());
		station.setType(station.getType());
		stationRepo.save(station);
		
		responseDto.setMessage("Sation saved");
		return ResponseEntity.status(HttpStatus.OK)
				.body(responseDto);
		
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getStationById(@PathVariable("id") Long id) {
		
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
		resStationDto.setStaff(airline.getStaff());
		resStationDto.setStaff(resStationDto.getStaff());
		
		return ResponseEntity.status(HttpStatus.OK).body(resStationDto);
	}
}
