package com.wcm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wcm.dto.ResponseDto;
import com.wcm.exception.ResourseNotFoundException;
import com.wcm.model.Wheel_Chair;
import com.wcm.repository.WheelChairRepository;

@Service
public class WheelChairService {
	@Autowired
	private WheelChairRepository wheelChairRepo;
	
	@Autowired
	private ResponseDto responseDto;

	public ResponseEntity<Object> UpdateStatus(Long id) {
		Wheel_Chair wc = wheelChairRepo.findById(id)
				.orElseThrow(()-> new ResourseNotFoundException("Invalid Wheel Chair ID"));

		wc.setWcStatus(!wc.getWcStatus());
		wheelChairRepo.save(wc);
		responseDto.setMessage("Status Updated");
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
}
