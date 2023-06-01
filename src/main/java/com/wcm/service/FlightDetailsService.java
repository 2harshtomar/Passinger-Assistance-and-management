package com.wcm.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.wcm.dto.ResponseDto;
import com.wcm.model.Flight_details;
import com.wcm.repository.FlightDetailsRepository;
@Service
public class FlightDetailsService {
	@Autowired
	private FlightDetailsRepository flightRepo;
	
	@Autowired
	private ResponseDto responseDto;
	
	public ResponseEntity<Object> updateFlightStatus(Long id) {
		Optional<Flight_details> optional = flightRepo.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid flight ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Flight_details flight = optional.get();
		if(Duration.between(LocalDateTime.now(), flight.getFromDateTime()).toHours() <= 48) {
			flight.setStatus("BOARDING");
		}
		if(LocalDateTime.now().isAfter(flight.getFromDateTime())) {
			flight.setStatus("DEPARTED");
		}
		if(LocalDateTime.now().isAfter(flight.getToDateTime())) {
			flight.setStatus("ARRIVED");
		}
		flightRepo.save(flight);
		responseDto.setMessage("Status Updated");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
//	@Scheduled(fixedDelay = 60000, initialDelay = 2000) 
	public void updateAllFlights() {
		List<Flight_details> flights = flightRepo.findAll();
		for(Flight_details flight: flights) {
			if(Duration.between(LocalDateTime.now(), flight.getFromDateTime()).toHours() <= 48) {
				flight.setStatus("BOARDING");
				flight.getCarrier().setStatus("IN-FLIGHT");
			}
			if(LocalDateTime.now().isAfter(flight.getFromDateTime())) {
				flight.setStatus("DEPARTED");
			}
			if(LocalDateTime.now().isAfter(flight.getToDateTime())) {
				flight.setStatus("LANDED");
				flight.getCarrier().setStatus("AVAILABLE");
				flight.getCarrier().setSeatsOccupied((long)0);
			}
			flightRepo.save(flight);
		}
	}
}
