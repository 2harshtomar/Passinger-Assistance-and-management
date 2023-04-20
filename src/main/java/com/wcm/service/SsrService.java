package com.wcm.service;

import java.security.Principal;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.wcm.dto.ResStaffSsrDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Ssr;
import com.wcm.model.Staff;
import com.wcm.repository.SsrRepository;
import com.wcm.repository.StaffRepository;

@Service
public class SsrService {
	@Autowired
	private SsrRepository ssrRepo;
	
	@Autowired
	private StaffRepository staffRepo;
	
	@Autowired
	private ResStaffSsrDto resStaffSsrDto;
	
	@Autowired 
	private ResponseDto responseDto;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private WheelChairService wheelChairService;
	
	public ResStaffSsrDto isSource(Principal principal) {
		Staff staff = staffRepo.findStaffDetails(principal.getName());
		Ssr ssr = ssrRepo.getSsrOnStaff(staff.getId());
		resStaffSsrDto.setSid(ssr.getsStaff().getId());
		resStaffSsrDto.setDid(ssr.getdStaff().getId());
		resStaffSsrDto.setPname(ssr.getPssengerDetails().getName());
		resStaffSsrDto.setPcontact(ssr.getPssengerDetails().getContact());
		resStaffSsrDto.setFlightNo(ssr.getPssengerDetails().getFlightDetails().getFlightNo());
		resStaffSsrDto.setFlStatus(ssr.getPssengerDetails().getFlightDetails().getStatus());
		resStaffSsrDto.setFromDateTime(ssr.getPssengerDetails().getFlightDetails().getFromDateTime());
		resStaffSsrDto.setTerminalNo(ssr.getPssengerDetails().getFlightDetails().getDestinationTerminalNo());
		resStaffSsrDto.setStNumber(ssr.getPssengerDetails().getFlightDetails().getDestinationStation().getStNumber());
		resStaffSsrDto.setToDateTime(ssr.getPssengerDetails().getFlightDetails().getToDateTime());
		resStaffSsrDto.setSsrStatus(ssr.getStatus());
		resStaffSsrDto.setArcived(ssr.isArcived());
		if(ssr.getsStaff().getId() == staff.getId()) {
			resStaffSsrDto.setTerminalNo(ssr.getPssengerDetails().getFlightDetails().getSourseTerminalNo());
			resStaffSsrDto.setStNumber(ssr.getPssengerDetails().getFlightDetails().getSourceStation().getStNumber());
		}
		return resStaffSsrDto;
	}
	
	public ResponseEntity<Object> updateArchiveStatus(Principal principal){
		System.out.println(principal.getName());
		Staff staff = staffRepo.findStaffDetails(principal.getName());
		System.out.println(staff.getId());
		Ssr ssr = ssrRepo.getSsrOnStaff(staff.getId());
		if(ssr.getdStaff().getId() == staff.getId()) {
			ssr.setArcived(true);
			ssr.setStatus("ARCHIVED");
			ssr.setCloseDateTime(LocalDateTime.now());
			staffService.updateStaffStatus(ssr.getdStaff().getId());
			wheelChairService.UpdateStatus(ssr.getdWheelChair().getId());
		}
		ssrRepo.save(ssr);
		responseDto.setMessage("SSR Arcived");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	public ResponseEntity<Object> updateSourceStaff(Principal principal){
		Staff staff = staffRepo.findStaffDetails(principal.getName());
		System.out.println(staff.getId());
		Ssr ssr = ssrRepo.getSsrOnStaff(staff.getId());
		System.out.println(ssr.getId());
		if(ssr.getsStaff().getId() == staff.getId()) {
			staffService.updateStaffStatus(ssr.getsStaff().getId());
			wheelChairService.UpdateStatus(ssr.getsWheelChair().getId());
			ssr.setStatus("ACTIVE-PASSENGER DEPARTED");
		}
		ssrRepo.save(ssr);
		responseDto.setMessage("Source staff and wheel chair status updated");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
}
