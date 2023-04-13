package com.wcm.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.dto.ResSsrDto;
import com.wcm.model.PassengerDetails;
import com.wcm.model.Ssr;
import com.wcm.model.Staff;
import com.wcm.model.Wheel_Chair;
import com.wcm.repository.SsrRepository;
import com.wcm.repository.StaffRepository;

@RestController
public class SsrController {
	
	@Autowired
	SsrRepository ssrrepository ;
	@Autowired
	StaffRepository staffrepository;
	
	
	@GetMapping("/get/ssr")
	public List<ResSsrDto> getAllSsr(){
		List<Ssr> list = ssrrepository.findAll();
		List<ResSsrDto> listDto = new ArrayList<>();
		for(Ssr s : list) {
			ResSsrDto dto = new ResSsrDto();
			dto.setId(s.getId());
			dto.setPname(s.getPssengerDetails().getName());
			dto.setPcontcat(s.getPssengerDetails().getContact());
			dto.setAirCraftName(s.getPssengerDetails().getFlightDetails().getAirCraftName());
			dto.setSource(s.getPssengerDetails().getFlightDetails().getSource());
			dto.setDestination(s.getPssengerDetails().getFlightDetails().getDestination());
			dto.setFlightNo(s.getPssengerDetails().getFlightDetails().getFlightNo());
			dto.setFromDateTime(s.getPssengerDetails().getFlightDetails().getFromDateTime());
			dto.setToDateTime(s.getPssengerDetails().getFlightDetails().getToDateTime());
			dto.setStatus(s.getStatus());
			
			listDto.add(dto);
		}
		return listDto;
	}
	
	@PostMapping("/add/ssr")
	public ResponseEntity<Object> addSsr(Ssr ssr){
		PassengerDetails pssengerDetails = ssr.getPssengerDetails();
		
		Staff staff = ssr.getStaff();
		
		List<Staff> list = staffrepository.findAll();
		for(Staff s : list) {
			if(s.getStatus() == "AVAILABLE") {
				s.setStatus("NOTAVIALABLE");
				staffrepository.save(s);
				ssr.setStaff(staff);
				break;
			}
		}

		Wheel_Chair wheelchair = ssr.getWheelChair();
		//Needs status of wheel chair so  if its free we can assign it to the ssr .
		
		ssr.setPssengerDetails(pssengerDetails);
		ssr.setStaff(staff);
		ssr.setStatus("INPROGRESS");
		ssr.setWheelChair(wheelchair);
		ssr.setOpenDateTime(LocalDateTime.now());
		ssr = ssrrepository.save(ssr);
		return ResponseEntity.status(HttpStatus.OK).body(ssr);
		
	}
	

}
