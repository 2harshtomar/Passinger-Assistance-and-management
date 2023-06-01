package com.wcm.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.dto.ResponseDto;
import com.wcm.model.PassengerDetails;
import com.wcm.model.PassengerNameRecord;
import com.wcm.model.Ssr;
import com.wcm.repository.PassengerRepository;
import com.wcm.repository.PnrRepository;
import com.wcm.repository.SsrRepository;
import com.wcm.service.CarrierService;
import com.wcm.service.PnrService;

@RestController
@RequestMapping("/api/pnr")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PnrController {
	
	@Autowired
	private PnrRepository pnrRepo;
	
	@Autowired
	private PnrService pnrService;
	
	@Autowired
	private CarrierService carrierService;
	
	@Autowired
	private PassengerRepository passengerRepository;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Autowired
	private SsrRepository ssrRepository;
	
	@PostMapping("/add")
	public ResponseEntity<Object> generatePnr(@RequestBody List<Long> ids){
		List<PassengerDetails> passengers = passengerRepository.findAllById(ids);
		PassengerNameRecord pnr = new PassengerNameRecord();
		pnr.setPnrNo(pnrService.GeneratePNR(passengers));
		pnr.setPassengers(passengers);
		pnr.setSeats( pnrService.GetSeatsName(passengers, passengers.get(0).getFlightDetails().getCarrier().getId()));
		pnr.setNumberOfSeats(String.valueOf(passengers.size()));
		pnr = pnrRepo.save(pnr);
		carrierService.updateSeats(passengers.get(0).getFlightDetails().getCarrier().getId(), passengers);
		System.out.println("done");
		return ResponseEntity.status(HttpStatus.OK).body(pnr);
	}
	@GetMapping("/get/{pnrNo}")
	public ResponseEntity<Object> getPnrDetails(@PathVariable("pnrNo") String pnrNo){
		
		PassengerNameRecord pnr = pnrRepo.getPNR(pnrNo);
		if(pnr == null) {
			responseDto.setMessage("Pnr is Invalid");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(pnrService.convertToDto(pnr));
	}
	@PutMapping("/set/ssr/{pid}/{sid}")
	public ResponseEntity<Object> setSsr(@PathVariable("sid") Long sid, @PathVariable("pid") Long pid) {
		Optional<Ssr> optionals = ssrRepository.findById(sid);
		Optional<PassengerNameRecord> optionalp = pnrRepo.findById(pid);
		
		Ssr ssr = optionals.get();
		PassengerNameRecord pnr = optionalp.get();
		
		pnr.setSsr(ssr);
		pnrRepo.save(pnr);
		
		responseDto.setMessage("pnr updated successfully");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
}
