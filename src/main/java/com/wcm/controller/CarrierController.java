package com.wcm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.dto.ReqCarrierDto;
import com.wcm.dto.ResCarrierDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Airline;
import com.wcm.model.Carrier;
import com.wcm.repository.AirlineRepository;
import com.wcm.repository.CarrierRepository;
import com.wcm.service.CarrierService;

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
	
	@Autowired
	private CarrierService carrierService;
	
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
	
	@GetMapping("/get/{cid}")
	public ResponseEntity<Object> getCarrier(@PathVariable("cid") Long cid){
		Optional<Carrier> optional = carrierRepository.findById(cid);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Carrier ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Carrier carrier = optional.get();
		return ResponseEntity.status(HttpStatus.OK).body(carrierService.convertToDto(carrier));
	}
	
	@GetMapping("get/all/{aid}")
	public ResponseEntity<Object> getAllCarrier(@PathVariable("aid") Long aid){
		Optional<Airline> optional = airlineRepository.findById(aid);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Airline ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		List<Carrier> carriers = carrierRepository.getAllCarriers(aid);
		List<ResCarrierDto> carrierDtos = new ArrayList<>();
		for(Carrier c: carriers) {
			ResCarrierDto dto = new ResCarrierDto();
			dto = carrierService.convertToDto(c);
			carrierDtos.add(dto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(carrierDtos);
	}
}
