package com.wcm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.dto.ReqCarrierDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Airline;
import com.wcm.model.Carrier;
import com.wcm.repository.AirlineRepository;
import com.wcm.repository.CarrierRepository;

@RestController
@RequestMapping("/api/carrier")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CarrierController {
	
	@Autowired
	private CarrierRepository carrierRepository;
	
	@Autowired
	private AirlineRepository airlineRepository;
	
	@Autowired
	private ResponseDto responseDto;
	
	@PostMapping("/add/{aid}")
	public ResponseEntity<Object> addCarrier(@PathVariable("aid") Long aid, @RequestBody ReqCarrierDto reqCarrierDto){
		Optional<Airline> optional = airlineRepository.findById(aid);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Airline ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Airline airline = optional.get();
		Carrier carrier = new Carrier();
		
		carrier.setAirline(airline);
		carrier.setName(reqCarrierDto.getName());
		carrier.setSeatsOccupied((long)0);
		carrier.setStatus("GROUNDED");
		carrier.setMaxNoSeats(reqCarrierDto.getMaxNoSeats());
		carrierRepository.save(carrier);
		responseDto.setMessage("Carrier saved");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
}
