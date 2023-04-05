package com.wcm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.wcm.model.Staff;
import com.wcm.repository.StaffRepository;

@Service
public class StaffService {
	
	@Autowired
	private StaffRepository staffRepo;
	
	public void updateStaffStatus(Long id) {
		Staff staff = staffRepo.findById(id)
				.orElseThrow(()-> new ResourceAccessException("Invalid staff ID"));
		// staff status "AVAILABLE" is temporary and will be changed
		staff.setStatus("AVAILABLE");
		staffRepo.save(staff);
	}
	
	public List<Staff> getStaffByCode(String code){ // code passed is station code NOT STAFF CODE
		// extract station code from st number ex- MAA-01 -> MAA
	      int sepPos = code.lastIndexOf("-");
	      String stcode = code.substring(0,sepPos);
//	      System.out.println(code + "->" + stcode);
	      List<Staff> staffList = staffRepo.findstaffByCode(stcode);
		
		return staffList;
		
	}
	
	}

