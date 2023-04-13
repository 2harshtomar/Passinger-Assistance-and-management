package com.wcm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.dto.ReqAirlineDto;
import com.wcm.dto.ResAirlineDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Airline;
import com.wcm.model.User;
import com.wcm.repository.AirlineRepository;
import com.wcm.repository.UserRepository;

@RestController
@RequestMapping("/api/airline")
public class AirlineController {

	@Autowired
	private AirlineRepository airlineRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResponseDto responseDto;

	@Autowired
	private ResAirlineDto resAirlineDto;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/add")
	public ResponseEntity<Object> addAirline(@RequestBody ReqAirlineDto reqAirlineDto) {

		User user = new User();
		user.setUsername(reqAirlineDto.getUsername());
		user.setPassword(reqAirlineDto.getPassword());
		user.setRole("AIRLINE");
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		user = userRepository.save(user);

		Airline airline = new Airline();
		airline.setName(reqAirlineDto.getName());
		airline.setAirlineCode(reqAirlineDto.getAirlineCode());
		airline.setOpType(reqAirlineDto.getOpType());
		airline.setFleet(reqAirlineDto.getFleet());
		airline.setUser(user);

		airlineRepository.save(airline);
		responseDto.setMessage("Airline registered successfully");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getAirlineById(@PathVariable("id") Long id) {

		Optional<Airline> optional = airlineRepository.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Airline ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Airline airline = optional.get();
		resAirlineDto.setId(airline.getId());
		resAirlineDto.setName(airline.getName());
		resAirlineDto.setAirlineCode(airline.getAirlineCode());
		resAirlineDto.setFleet(airline.getFleet());
		resAirlineDto.setOpType(airline.getOpType());
		resAirlineDto.setUserId(airline.getUser().getId());
		resAirlineDto.setUsername(airline.getUser().getUsername());
		resAirlineDto.setStaff(airline.getStaff());
		resAirlineDto.setWheel_chair(airline.getWheel_chair());

		return ResponseEntity.status(HttpStatus.OK).body(resAirlineDto);
	}
}
