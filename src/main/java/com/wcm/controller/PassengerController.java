package com.wcm.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.dto.ResPassengerDetailsDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Flight_details;
import com.wcm.model.PassengerDetails;
import com.wcm.repository.FlightDetailsRepository;
import com.wcm.repository.PassengerRepository;

@RestController
@RequestMapping("/api/passenger")
@CrossOrigin(origins = "")
public class PassengerController {
	
	/* Author : Aaditya Mohan
	 * emp id : 2000081375
	 */
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	@Autowired
	private FlightDetailsRepository flightDetailsRepository;
	
	@Autowired
	private ResponseDto responseDto;
	
	@PostMapping("/add/{id}")
	public ResponseEntity<Object> addPassenger(@RequestBody PassengerDetails passenger, @PathVariable("id") Long id) {
		PassengerDetails passengerDB = new PassengerDetails();
		Optional<Flight_details> optional = flightDetailsRepository.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Flight ID is Invalid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		
		Flight_details flightDetails = optional.get();
		passengerDB.setName(passenger.getName());
		passengerDB.setAddress(passenger.getAddress());
		passengerDB.setContact(passenger.getContact());
		passengerDB.setFlightDetails(flightDetails);
		
		passengerRepository.save(passengerDB);
		responseDto.setMessage("Passenger added successfully");
		
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getPassengerById(@PathVariable("id") Long id) {
		ResPassengerDetailsDto resPassengerDetailsDto = new ResPassengerDetailsDto();
		Optional<PassengerDetails> optional = passengerRepository.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Passenger ID is Invalid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		
		PassengerDetails passengerDB = optional.get();
		resPassengerDetailsDto.setId(passengerDB.getId());
		resPassengerDetailsDto.setName(passengerDB.getName());
		resPassengerDetailsDto.setContact(passengerDB.getContact());
		resPassengerDetailsDto.setAddress(passengerDB.getAddress());
		resPassengerDetailsDto.setFlightNo(passengerDB.getFlightDetails().getFlightNo());
		resPassengerDetailsDto.setFromDateTime(passengerDB.getFlightDetails().getFromDateTime());
		resPassengerDetailsDto.setToDateTime(passengerDB.getFlightDetails().getToDateTime());
		resPassengerDetailsDto.setAirCraftName(passengerDB.getFlightDetails().getAirCraftName());
		resPassengerDetailsDto.setStatus(passengerDB.getFlightDetails().getStatus());
		resPassengerDetailsDto.setSourseTerminalNo(passengerDB.getFlightDetails().getSourseTerminalNo());
		resPassengerDetailsDto.setDestinationTerminalNo(passengerDB.getFlightDetails().getDestinationTerminalNo());
		resPassengerDetailsDto.setSourceStationNumber(passengerDB.getFlightDetails().getSourceStation().getStNumber());
		resPassengerDetailsDto.setSourceStationName(passengerDB.getFlightDetails().getSourceStation().getName());
		resPassengerDetailsDto.setSourceStationLocation(passengerDB.getFlightDetails().getSourceStation().getLocation());
		resPassengerDetailsDto.setDestStationNumber(passengerDB.getFlightDetails().getDestinationStation().getStNumber());
		resPassengerDetailsDto.setDestStationName(passengerDB.getFlightDetails().getDestinationStation().getName());
		resPassengerDetailsDto.setDestStationLocation(passengerDB.getFlightDetails().getSourceStation().getLocation());
		resPassengerDetailsDto.setAirlineName(passengerDB.getFlightDetails().getAirline().getName());
		resPassengerDetailsDto.setAirlineCode(passengerDB.getFlightDetails().getAirline().getAirlineCode());
		
		return ResponseEntity.status(HttpStatus.OK).body(resPassengerDetailsDto);
	}

}
