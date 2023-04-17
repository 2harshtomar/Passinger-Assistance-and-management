package com.wcm.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.dto.PassengerResSsrDto;
import com.wcm.dto.ResSsrDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.PassengerDetails;
import com.wcm.model.Ssr;
import com.wcm.model.Staff;
import com.wcm.model.Wheel_Chair;
import com.wcm.repository.PassengerRepository;
import com.wcm.repository.SsrRepository;
import com.wcm.service.AirlineService;

@RestController
@RequestMapping("/api/ssr")
public class SsrController {
//	private Long id;
//	private LocalDateTime openDateTime;
//	private LocalDateTime closeDateTime;
//	private String status;
	
	/* Author : Aaditya Mohan
	 * emp id : 2000081375
	 */
	
	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private AirlineService airlineService;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Autowired
	private ResSsrDto resSsrDto;
	
	@Autowired
	private SsrRepository ssrRepo;
	
	@Autowired
	private PassengerResSsrDto passengerResSsrDto;
	
	@PostMapping("/add/{passengerId}")
	public ResponseEntity<Object> raiseSsr(@PathVariable("passengerId") Long pid){
		Optional<PassengerDetails> optional = passengerRepo.findById(pid);
		PassengerDetails passenger = optional.get();
		Ssr ssr = new Ssr();
		ssr.setPssengerDetails(passenger);
		ssr.setOpenDateTime(LocalDateTime.now());
		ssr.setStatus("BOARDED-NA");
		//getting wheel chair & staff from source station
		List<Object> sourceStaffWcPair = airlineService.RequestStation(passenger.getFlightDetails().getSourceStation().getStNumber());
		//getting wheel chair & staff from destination station
		List<Object> DestinationStaffWcPair = airlineService.RequestStation(passenger.getFlightDetails().getDestinationStation().getStNumber());
		// apply for loop and access data from list
		for(Object obj:sourceStaffWcPair) {
			if(obj.getClass() == Staff.class) {
				ssr.setsStaff((Staff) obj);
			}
			if(obj.getClass() == Wheel_Chair.class) {
				ssr.setsWheelChair((Wheel_Chair) obj);
			}
		}
		for(Object obj:DestinationStaffWcPair) {
			if(obj.getClass() == Staff.class) {
				ssr.setdStaff((Staff) obj);
			}
			if(obj.getClass() == Wheel_Chair.class) {
				ssr.setdWheelChair((Wheel_Chair) obj);
			}
		}
		responseDto.setMessage("SSR raised successfully");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		
	}
	
	@GetMapping("/staff/get/{id}")
	public ResponseEntity<Object> getStaffSsr(@PathVariable("id") Long id) {
		Optional<Ssr> optional = ssrRepo.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Ssr ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Ssr ssr = optional.get();
		resSsrDto.setPname(ssr.getPssengerDetails().getName());
		resSsrDto.setPcontact(ssr.getPssengerDetails().getContact());
		resSsrDto.setFlightNo(ssr.getPssengerDetails().getFlightDetails().getFlightNo());
		resSsrDto.setFlStatus(ssr.getPssengerDetails().getFlightDetails().getStatus());
		resSsrDto.setFromDateTime(ssr.getPssengerDetails().getFlightDetails().getFromDateTime());
		resSsrDto.setToDateTime(ssr.getPssengerDetails().getFlightDetails().getToDateTime());
		resSsrDto.setSourseTerminalNo(ssr.getPssengerDetails().getFlightDetails().getSourseTerminalNo());
		resSsrDto.setDestTerminalNo(ssr.getPssengerDetails().getFlightDetails().getDestinationTerminalNo());
		resSsrDto.setsStNumber(ssr.getPssengerDetails().getFlightDetails().getSourceStation().getStNumber());
		resSsrDto.setdStNumber(ssr.getPssengerDetails().getFlightDetails().getDestinationStation().getStNumber());
		resSsrDto.setSsrStatus(ssr.getStatus());
		return ResponseEntity.status(HttpStatus.OK).body(resSsrDto);
		
	}
	@GetMapping("/passenger/get/{id}")
	public ResponseEntity<Object> getPassengerSsr(@PathVariable("id") Long id){
		Optional<Ssr> optional = ssrRepo.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Ssr ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Ssr ssr = optional.get();
		String status = ssr.getPssengerDetails().getFlightDetails().getStatus();
		if(status == "BOARDING" || status == "BOARDED-NA") {
			passengerResSsrDto.setAirlineName(ssr.getPssengerDetails().getFlightDetails().getAirline().getName());
			passengerResSsrDto.setStaffContact(ssr.getsStaff().getContact());
			passengerResSsrDto.setStaffName(ssr.getsStaff().getName());
			passengerResSsrDto.setTerminalNo(ssr.getPssengerDetails().getFlightDetails().getSourseTerminalNo());
		}
		else {
			passengerResSsrDto.setAirlineName(ssr.getPssengerDetails().getFlightDetails().getAirline().getName());
			passengerResSsrDto.setStaffContact(ssr.getdStaff().getContact());
			passengerResSsrDto.setStaffName(ssr.getdStaff().getName());
			passengerResSsrDto.setTerminalNo(ssr.getPssengerDetails().getFlightDetails().getDestinationTerminalNo());
		}
		return ResponseEntity.status(HttpStatus.OK).body(passengerResSsrDto);
	}
}
	
