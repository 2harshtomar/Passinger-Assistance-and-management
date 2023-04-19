package com.wcm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.dto.ReqFlightDetailsDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Airline;
import com.wcm.model.Flight_details;
import com.wcm.model.Station;
import com.wcm.repository.AirlineRepository;
import com.wcm.repository.FlightDetailsRepository;
import com.wcm.repository.StationRepository;
import com.wcm.service.FlightDetailsService;

@RestController
@RequestMapping("/api/flight/details")
public class FlightDetialsController {
	
		@Autowired
		private FlightDetailsRepository flightDetailsRepository;
		
		@Autowired
		private ResponseDto responseDto;
		
		@Autowired
		private ReqFlightDetailsDto resFlightDto;
		
		@Autowired
		private StationRepository stationRepository;
		
		@Autowired
		private AirlineRepository airlineRepository;
		
		@Autowired
		private FlightDetailsService flightService;
		
		@PostMapping("/add/{sid}/{did}/{aid}")
		public ResponseEntity<Object> addFlight(@PathVariable("sid") Long sid, @PathVariable("did") Long did, @PathVariable("aid") Long aid, @RequestBody ReqFlightDetailsDto flightDto){
			Optional<Station> soptional = stationRepository.findById(sid);
			if(soptional.isEmpty()) {
				responseDto.setMessage("Invalid Source station ID");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
			Optional<Station> doptional = stationRepository.findById(did);
			if(doptional.isEmpty()) {
				responseDto.setMessage("Invalid Destination station ID");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
			Optional<Airline> aoptional = airlineRepository.findById(aid);
			if(aoptional.isEmpty()) {
				responseDto.setMessage("Invalid Airline ID");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
			
			Station sourceStation = soptional.get();
			Station destinationStation = doptional.get();
			Airline airline = aoptional.get();
			
			Flight_details flight = new Flight_details();
			flight.setFlightNo(flightDto.getFlightNo());
			flight.setFromDateTime(flightDto.getFromDateTime());
			flight.setToDateTime(flightDto.getToDateTime());
			flight.setSourseTerminalNo(flightDto.getSourseTerminalNo());
			flight.setDestinationTerminalNo(flightDto.getDestinationTerminalNo());
			flight.setStatus("BOARDED-NA");
			flight.setAirCraftName(flightDto.getAirCraftName());
			flight.setSourceStation(sourceStation);
			flight.setDestinationStation(destinationStation);
			flight.setAirline(airline);
			
			flightDetailsRepository.save(flight);
			responseDto.setMessage("Flight data recorded");
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		}
		
		@GetMapping("/get/{id}")
		public ResponseEntity<Object> getFlight(@PathVariable("id") Long id){
			Optional<Flight_details> optional = flightDetailsRepository.findById(id);
			if(optional.isEmpty()) {
				responseDto.setMessage("Invalid fligh ID");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
			}
			
			Flight_details flight = optional.get();
			resFlightDto.setFlightNo(flight.getFlightNo());
			resFlightDto.setAirCraftName(flight.getAirCraftName());
			resFlightDto.setSourceSationName(flight.getSourceStation().getName());
			resFlightDto.setFromDateTime(flight.getFromDateTime());
			resFlightDto.setSourseTerminalNo(flight.getSourseTerminalNo());
			resFlightDto.setDestinationStationName(flight.getDestinationStation().getName());
			resFlightDto.setToDateTime(flight.getToDateTime());
			resFlightDto.setDestinationTerminalNo(flight.getDestinationTerminalNo());
			resFlightDto.setStatus(flight.getStatus());
			
			return ResponseEntity.status(HttpStatus.OK).body(resFlightDto);
		}
		@PutMapping("/update/flight/{fid}")
		public ResponseEntity<Object> updateFlightDetails(@PathVariable("fid") Long fid){
			return flightService.updateFlightStatus(fid);
			
		}
}
