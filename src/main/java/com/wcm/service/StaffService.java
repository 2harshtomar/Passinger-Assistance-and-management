package com.wcm.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Staff;
import com.wcm.model.User;
import com.wcm.repository.AirlineRepository;
import com.wcm.repository.StaffRepository;
import com.wcm.repository.StationRepository;
import com.wcm.repository.UserRepository;

@Service
public class StaffService {

	@Autowired
	private StaffRepository staffRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AirlineRepository airlineRepo;

	@Autowired
	private StationRepository stationRepo;
	
	@Autowired
	private ResponseDto responseDto;
	
	private final static String ACCOUNT_SID = "AC414fae3bea4bf8aad1545c1265e2523d";
	private final static String AUTH_ID = "acab93af96dc2bf6c85b6464fa6fdd95";

	static {
	   Twilio.init(ACCOUNT_SID, AUTH_ID);
	}

	public List<Staff> getStaff(Principal principal){ // code passed is station code NOT STAFF CODE

		String username = principal.getName();
		User user = userRepo.findByUsername(username);
		String code = "";
		if(user.getRole().equalsIgnoreCase("AIRLINE")) {
			code = airlineRepo.getAirlineCode(username);
		}
		if(user.getRole().equalsIgnoreCase("STATION")) {
			code = stationRepo.GetStationCode(username);
		}
		// extract station code from st number ex- MAA-01 -> MAA
	    int sepPos = code.lastIndexOf("-");
	    String stcode = code.substring(0,sepPos);
//	    System.out.println(code + "->" + stcode);
	    List<Staff> staffList = staffRepo.findstaffByCode(stcode);

		return staffList;

	}
	public ResponseEntity<Object> updateStaffStatus(Long id) {
		Optional<Staff> optional = staffRepo.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Staff ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		
		Staff staff = optional.get();
		String status = "";
		
		if(staff.getStatus().equals("AVAILABLE")) {
			status = "ENGAGED";
		}
		if(staff.getStatus().equals("ENGAGED")) {
			status = "AVAILABLE";
		}
		staff.setStatus(status);
		staffRepo.save(staff);
		responseDto.setMessage("Status Updated");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	public ResponseEntity<Object> sendSMS(String msg) {
	      Message.creator(new PhoneNumber("+916397382080"), new PhoneNumber("+15074456938"),
	    	         msg).create();
	      responseDto.setMessage("Message sent");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	      
	}
	}

