package com.wcm.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.demo.dto.ReqStaffDto;
import com.wcm.demo.dto.ResStaffDto;
import com.wcm.demo.dto.ResponseDto;
import com.wcm.demo.model.Staff;
import com.wcm.demo.model.User;
import com.wcm.demo.repository.StaffRepository;
import com.wcm.demo.repository.UserRepository;

@RestController
//@RequestMapping("/api/staff")
public class StaffController {
	@Autowired
	private StaffRepository staffRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Autowired
	private ResStaffDto resStaffDto;
	
	@PostMapping("/api/staff/add")
	public ResponseEntity<Object> addStaff(@RequestBody ReqStaffDto reqStaffDto){
		User user = new User();
		user.setUsername(reqStaffDto.getUsername());
		user.setPassword(reqStaffDto.getPassword());
		user.setRole("STAFF");
		// password encoder will be here
		user = userRepository.save(user);
		
		Staff staff = new Staff();
		staff.setName(reqStaffDto.getName());
		staff.setStaffCode(reqStaffDto.getStaffCode());
		staff.setStatus(reqStaffDto.getStatus());
		
		staffRepo.save(staff);
		responseDto.setMessage("Staff registered successfully");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getStaffById(@PathVariable("id") Long id){
		Optional<Staff> optional = staffRepo.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Staff ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		
		Staff staff = optional.get();
		resStaffDto.setName(staff.getName());
		resStaffDto.setStaffCode(staff.getStaffCode());
		resStaffDto.setStatus(staff.getStatus());
		resStaffDto.setUsername(staff.getUser().getUsername());
		
		return ResponseEntity.status(HttpStatus.OK).body(resStaffDto);
	}
}
