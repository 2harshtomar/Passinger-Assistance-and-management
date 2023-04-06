package com.wcm.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

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
 	
	public void updateStaffStatus(Long id) {
		Staff staff = staffRepo.findById(id)
				.orElseThrow(()-> new ResourceAccessException("Invalid staff ID"));
		// staff status "AVAILABLE" is temporary and will be changed
		staff.setStatus("AVAILABLE");
		staffRepo.save(staff);
	}
	
	public List<Staff> getStaffByCode(Principal principal){ // code passed is station code NOT STAFF CODE
		
		String username = principal.getName();
		User user = userRepo.getUserByusername(username);
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
	
	}

