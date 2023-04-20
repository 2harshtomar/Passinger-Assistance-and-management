package com.wcm.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.dto.ReqStaffDto;
import com.wcm.dto.ResStaffDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Staff;
import com.wcm.model.User;
import com.wcm.repository.StaffRepository;
import com.wcm.repository.UserRepository;
import com.wcm.service.StaffService;
import com.wcm.service.StationRouterService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/staff")
public class StaffController {
	@Autowired
	private StaffRepository staffRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResponseDto responseDto;

	@Autowired
	private ResStaffDto resStaffDto;
	
	@Autowired
	private StationRouterService srs;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private StaffService staffService;

	@PostMapping("/add/{userId}")
	public ResponseEntity<Object> addStaff(@PathVariable("userId") Long id,@RequestBody ReqStaffDto reqStaffDto){
		User user = new User();
		user.setUsername(reqStaffDto.getUsername());
		user.setPassword(reqStaffDto.getPassword());
		user.setRole("STAFF");
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		user = userRepository.save(user);
		
		Staff staff = new Staff();
		staff.setName(reqStaffDto.getName());
		staff.setContact(reqStaffDto.getContact());
		staff.setEmail(reqStaffDto.getEmail());
		staff.setStaffCode(srs.getEntity(id));
		staff.setStatus("AVAILABLE");
		staff.setUser(user);

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
		resStaffDto.setContact(staff.getContact());
		resStaffDto.setEmail(staff.getEmail());
		resStaffDto.setUsername(staff.getUser().getUsername());

		return ResponseEntity.status(HttpStatus.OK).body(resStaffDto);
	}
	// get all 
	@GetMapping("/get/all")
	public List<ResStaffDto> getAllStaff(Principal principal){
		return staffService.getStaff(principal);
		
	}
}
