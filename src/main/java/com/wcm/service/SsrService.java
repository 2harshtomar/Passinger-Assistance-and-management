package com.wcm.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcm.dto.ResStaffSsrDto;
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
	
	public ResStaffSsrDto isSource(Principal principal) {
		Staff staff = staffRepo.findStaffDetails(principal.getName());
		Ssr ssr = ssrRepo.getSsrOnStaff(staff.getId());
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
	
	
}
