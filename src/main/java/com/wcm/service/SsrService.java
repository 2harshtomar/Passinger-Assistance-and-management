package com.wcm.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
	
	public ResponseEntity<Object> isSource(Principal principal) {
		Staff staff = staffRepo.findStaffDetails(principal.getName());
		List<Ssr> ssrs  = ssrRepo.getSsrOnStaff(staff.getId());
		for (Ssr s: ssrs) {
			if(staff.getId() == s.getsStaff().getId() || staff.getId() == s.getdStaff().getId()) {
				resStaffSsrDto.setId(s.getId());
				resStaffSsrDto.setSid(s.getsStaff().getId());
				resStaffSsrDto.setDid(s.getdStaff().getId());
				resStaffSsrDto.setPname(s.getPssengerDetails().getName());
				resStaffSsrDto.setPcontact(s.getPssengerDetails().getContact());
				resStaffSsrDto.setFlightNo(s.getPssengerDetails().getFlightDetails().getFlightNo());
				resStaffSsrDto.setFlStatus(s.getPssengerDetails().getFlightDetails().getStatus());
				resStaffSsrDto.setFromDateTime(s.getPssengerDetails().getFlightDetails().getFromDateTime());
				resStaffSsrDto.setTerminalNo(s.getPssengerDetails().getFlightDetails().getDestinationTerminalNo());
				resStaffSsrDto.setStNumber(s.getPssengerDetails().getFlightDetails().getDestinationStation().getStNumber());
				resStaffSsrDto.setToDateTime(s.getPssengerDetails().getFlightDetails().getToDateTime());
				resStaffSsrDto.setSsrStatus(s.getStatus());
				resStaffSsrDto.setArcived(s.isArcived());
				if(s.getsStaff().getId() == staff.getId()) {
					resStaffSsrDto.setTerminalNo(s.getPssengerDetails().getFlightDetails().getSourseTerminalNo());
					resStaffSsrDto.setStNumber(s.getPssengerDetails().getFlightDetails().getSourceStation().getStNumber());
				}
				
			}
			else {
				resStaffSsrDto = null;
			}

		}
		return ResponseEntity.status(HttpStatus.OK).body(resStaffSsrDto);
	}
	
	public ResponseEntity<Object> getStaffInfo(Long id) {
		Optional<Staff> optional = staffRepo.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Staff ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Staff staff = optional.get();
		List<Ssr> ssrs  = ssrRepo.getSsrOnStaff(staff.getId());
		for (Ssr s: ssrs) {
			if(staff.getId() == s.getsStaff().getId() || staff.getId() == s.getdStaff().getId()) {
				resStaffSsrDto.setId(s.getId());
				resStaffSsrDto.setSid(s.getsStaff().getId());
				resStaffSsrDto.setDid(s.getdStaff().getId());
				resStaffSsrDto.setPname(s.getPssengerDetails().getName());
				resStaffSsrDto.setPcontact(s.getPssengerDetails().getContact());
				resStaffSsrDto.setFlightNo(s.getPssengerDetails().getFlightDetails().getFlightNo());
				resStaffSsrDto.setFlStatus(s.getPssengerDetails().getFlightDetails().getStatus());
				resStaffSsrDto.setFromDateTime(s.getPssengerDetails().getFlightDetails().getFromDateTime());
				resStaffSsrDto.setTerminalNo(s.getPssengerDetails().getFlightDetails().getDestinationTerminalNo());
				resStaffSsrDto.setStNumber(s.getPssengerDetails().getFlightDetails().getDestinationStation().getStNumber());
				resStaffSsrDto.setToDateTime(s.getPssengerDetails().getFlightDetails().getToDateTime());
				resStaffSsrDto.setSsrStatus(s.getStatus());
				resStaffSsrDto.setArcived(s.isArcived());
				if(s.getsStaff().getId() == staff.getId()) {
					resStaffSsrDto.setTerminalNo(s.getPssengerDetails().getFlightDetails().getSourseTerminalNo());
					resStaffSsrDto.setStNumber(s.getPssengerDetails().getFlightDetails().getSourceStation().getStNumber());
				}
				
			}
			else {
				resStaffSsrDto = null;
			}

		}
		return ResponseEntity.status(HttpStatus.OK).body(resStaffSsrDto);
	}
	
	public ResponseEntity<Object> updateArchiveStatus(Principal principal){
		System.out.println(principal.getName());
		Staff staff = staffRepo.findStaffDetails(principal.getName());
		System.out.println(staff.getId());
		List<Ssr> ssrs  = ssrRepo.getSsrOnStaff(staff.getId());
		for (Ssr s: ssrs) {
			if(staff.getId() == s.getsStaff().getId() || staff.getId() == s.getdStaff().getId()) {
				if(s.getdStaff().getId() == staff.getId()) {
					s.setArcived(true);
					s.setStatus("ARCHIVED");
					s.setCloseDateTime(LocalDateTime.now());
					staffService.updateStaffStatus(s.getdStaff().getId());
					wheelChairService.UpdateStatus(s.getdWheelChair().getId());
				}
				ssrRepo.save(s);
			}
		}
		responseDto.setMessage("SSR Archived");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	public ResponseEntity<Object> updateSourceStaff(Principal principal){
		Staff staff = staffRepo.findStaffDetails(principal.getName());
		List<Ssr> ssrs  = ssrRepo.getSsrOnStaff(staff.getId());
		for (Ssr s: ssrs) {
			if(staff.getId() == s.getsStaff().getId() || staff.getId() == s.getdStaff().getId()) {
				if(s.getsStaff().getId() == staff.getId()) {
					staffService.updateStaffStatus(s.getsStaff().getId());
					wheelChairService.UpdateStatus(s.getsWheelChair().getId());
					s.setStatus("ACTIVE-PASSENGER DEPARTED");
				}
				ssrRepo.save(s);
			}
		}

		responseDto.setMessage("Source staff and wheel chair status updated");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	// same method by passing id
	public ResponseEntity<Object> updateSourceStaffbyID(Long id){
		Optional<Staff> optional = staffRepo.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Staff ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Staff staff = optional.get();
		List<Ssr> ssrs  = ssrRepo.getSsrOnStaff(staff.getId());
		for (Ssr s: ssrs) {
			if(staff.getId() == s.getsStaff().getId() || staff.getId() == s.getdStaff().getId()) {
				if(s.getsStaff().getId() == staff.getId()) {
					staffService.updateStaffStatus(s.getsStaff().getId());
					wheelChairService.UpdateStatus(s.getsWheelChair().getId());
					s.setStatus("ACTIVE-PASSENGER DEPARTED");
				}
				ssrRepo.save(s);
				resStaffSsrDto = convertToDto(s);
			}
		}

//		responseDto.setMessage("Source staff and wheel chair status updated");
		return ResponseEntity.status(HttpStatus.OK).body(resStaffSsrDto);
	}
	
	// same method by passing id
	public ResponseEntity<Object> updateArchiveStatusbyID(Long id){
		Optional<Staff> optional = staffRepo.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Staff ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Staff staff = optional.get();
		List<Ssr> ssrs  = ssrRepo.getSsrOnStaff(staff.getId());
		for (Ssr s: ssrs) {
			if(staff.getId() == s.getsStaff().getId() || staff.getId() == s.getdStaff().getId()) {
				if(s.getdStaff().getId() == staff.getId()) {
					s.setArcived(true);
					s.setStatus("ARCHIVED");
					s.setCloseDateTime(LocalDateTime.now());
					staffService.updateStaffStatus(s.getdStaff().getId());
					wheelChairService.UpdateStatus(s.getdWheelChair().getId());
				}
				ssrRepo.save(s);
				resStaffSsrDto = convertToDto(s);
			}
		}
//		responseDto.setMessage("SSR Archived");
		return ResponseEntity.status(HttpStatus.OK).body(resStaffSsrDto);
	}
	
	public ResStaffSsrDto convertToDto(Ssr s){
		resStaffSsrDto.setId(s.getId());
		resStaffSsrDto.setSid(s.getsStaff().getId());
		resStaffSsrDto.setDid(s.getdStaff().getId());
		resStaffSsrDto.setPname(s.getPssengerDetails().getName());
		resStaffSsrDto.setPcontact(s.getPssengerDetails().getContact());
		resStaffSsrDto.setFlightNo(s.getPssengerDetails().getFlightDetails().getFlightNo());
		resStaffSsrDto.setFlStatus(s.getPssengerDetails().getFlightDetails().getStatus());
		resStaffSsrDto.setFromDateTime(s.getPssengerDetails().getFlightDetails().getFromDateTime());
		resStaffSsrDto.setTerminalNo(s.getPssengerDetails().getFlightDetails().getDestinationTerminalNo());
		resStaffSsrDto.setStNumber(s.getPssengerDetails().getFlightDetails().getDestinationStation().getStNumber());
		resStaffSsrDto.setToDateTime(s.getPssengerDetails().getFlightDetails().getToDateTime());
		resStaffSsrDto.setSsrStatus(s.getStatus());
		resStaffSsrDto.setArcived(s.isArcived());
		
		return resStaffSsrDto;
	}
}
