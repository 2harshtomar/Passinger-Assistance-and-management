package com.wcm.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wcm.dto.ResponseDto;
import com.wcm.exception.ResourseNotFoundException;
import com.wcm.model.User;
import com.wcm.model.Wheel_Chair;
import com.wcm.repository.AirlineRepository;
import com.wcm.repository.StationRepository;
import com.wcm.repository.UserRepository;
import com.wcm.repository.WheelChairRepository;

@Service
public class WheelChairService {
	@Autowired
	private WheelChairRepository wheelChairRepo;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AirlineRepository airlineRepo;
	
	@Autowired
	private StationRepository stationRepo;

	public ResponseEntity<Object> UpdateStatus(Long id) {
		Wheel_Chair wc = wheelChairRepo.findById(id)
				.orElseThrow(()-> new ResourseNotFoundException("Invalid Wheel Chair ID"));

		wc.setWcStatus(!wc.getWcStatus());
		wheelChairRepo.save(wc);
		responseDto.setMessage("Status Updated");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
	
	public List<Wheel_Chair> getStaff(Principal principal){ // code passed is station code NOT STAFF CODE

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
	    List<Wheel_Chair> wcList = wheelChairRepo.getWheelChairs(stcode);

		return wcList;

	}
}
