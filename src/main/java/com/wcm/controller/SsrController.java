package com.wcm.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.wcm.service.SsrService;
import com.wcm.service.StaffService;
import com.wcm.service.WheelChairService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/ssr")
public class SsrController {

	
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
	
	@Autowired
	private WheelChairService wheelChairService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private SsrService ssrService;
	
	@PostMapping("/add/{passengerId}")
	public ResponseEntity<Object> raiseSsr(@PathVariable("passengerId") Long pid){
		Optional<PassengerDetails> optional = passengerRepo.findById(pid);
		PassengerDetails passenger = optional.get();
		Ssr ssr = new Ssr();
		ssr.setPssengerDetails(passenger);
		ssr.setOpenDateTime(LocalDateTime.now());
		ssr.setStatus("ACTIVE");
		ssr.setArcived(false);
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
		ssrRepo.save(ssr);
		
		staffService.sendSMS(ssr.getsStaff().getContact(), ssr.getsStaff().getName(), ssr.getPssengerDetails().getName(), ssr.getPssengerDetails().getContact(), ssr.getPssengerDetails().getFlightDetails().getSourseTerminalNo(), ssr.getPssengerDetails().getFlightDetails().getSourceStation().getStNumber(), ssr.getPssengerDetails().getFlightDetails().getFromDateTime());
		staffService.sendSMS(ssr.getdStaff().getContact(), ssr.getdStaff().getName(), ssr.getPssengerDetails().getName(), ssr.getPssengerDetails().getContact(), ssr.getPssengerDetails().getFlightDetails().getDestinationTerminalNo(), ssr.getPssengerDetails().getFlightDetails().getDestinationStation().getStNumber(), ssr.getPssengerDetails().getFlightDetails().getToDateTime());
		responseDto.setMessage("SSR raised successfully");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		
	}
	// connect this to staff dashboard assign tasks
	@GetMapping("/staff/get/ssrInfo")
	public ResponseEntity<Object> getSsrDetailsForSatff(Principal principal) {
		return ssrService.isSource(principal);
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
		resSsrDto.setIsArcived(ssr.isArcived());
		resSsrDto.setSid(ssr.getsStaff().getId());
		resSsrDto.setDid(ssr.getdStaff().getId());
		
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
			passengerResSsrDto.setArcived(ssr.isArcived());
		}
		else {
			passengerResSsrDto.setAirlineName(ssr.getPssengerDetails().getFlightDetails().getAirline().getName());
			passengerResSsrDto.setStaffContact(ssr.getdStaff().getContact());
			passengerResSsrDto.setStaffName(ssr.getdStaff().getName());
			passengerResSsrDto.setTerminalNo(ssr.getPssengerDetails().getFlightDetails().getDestinationTerminalNo());
			passengerResSsrDto.setArcived(ssr.isArcived());
		}
		return ResponseEntity.status(HttpStatus.OK).body(passengerResSsrDto);
	}
	@PutMapping("/staff/updateArciveStatus/{ssid}")
	public ResponseEntity<Object> updateArchiveStatus(@PathVariable("ssid") Long ssid){
		Optional<Ssr> soptional = ssrRepo.findById(ssid);
		if(soptional.isEmpty()) {
			responseDto.setMessage("Invalid Ssr ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Ssr ssr = soptional.get();
		ssr.setArcived(true);
		ssr.setStatus("ARCHIVED");
		ssr.setCloseDateTime(LocalDateTime.now());
		staffService.updateStaffStatus(ssr.getdStaff().getId());
		wheelChairService.UpdateStatus(ssr.getdWheelChair().getId());
		ssrRepo.save(ssr);
		responseDto.setMessage("SSR Arcived");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	@PutMapping("/update/source/staff/{ssid}")
	public ResponseEntity<Object> updateSourceStaff(@PathVariable("ssid") Long ssid){
		Optional<Ssr> soptional = ssrRepo.findById(ssid);
		if(soptional.isEmpty()) {
			responseDto.setMessage("Invalid Ssr ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Ssr ssr = soptional.get();
		staffService.updateStaffStatus(ssr.getsStaff().getId());
		wheelChairService.UpdateStatus(ssr.getsWheelChair().getId());
		ssr.setStatus("ACTIVE-PASSENGER DEPARTED");
		ssrRepo.save(ssr);
		responseDto.setMessage("Source staff and wheel chair status updated");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	// if staff id matches destination staff id in ssr
	@PutMapping("/staff/updateArciveStatus/principal")
	public ResponseEntity<Object> updateArchiveStatusPrincipal(Principal principal){
		return ssrService.updateArchiveStatus(principal);
	}
	// if staff id matches source staff id in ssr
	@PutMapping("/update/source/staff/principal")
	public ResponseEntity<Object> updateSourceStaffPrincipal(Principal principal){
		return ssrService.updateSourceStaff(principal);
	}
	@GetMapping("/staff/get/ssrInfo/id/{staffId}")
	public ResponseEntity<Object> getStaffInfo(@PathVariable("staffId") Long sid){
		return ssrService.getStaffInfo(sid);
		
	}
	
	// same api's but we are passing id's instead of principal
	@PutMapping("/staff/updateArciveStatus/id/{id}")
	public ResponseEntity<Object> updateArchiveStatusPrincipalbyID(@PathVariable("id") Long id){
		return ssrService.updateArchiveStatusbyID(id);
	}
	
	@PutMapping("/update/source/staff/id/{id}")
	public ResponseEntity<Object> updateSourceStaffPrincipalbyID(@PathVariable("id") Long id){
		return ssrService.updateSourceStaffbyID(id);
	}
}
	
