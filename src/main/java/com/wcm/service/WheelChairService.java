package com.wcm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcm.exception.ResourseNotFoundException;
import com.wcm.model.Wheel_Chair;
import com.wcm.repository.WheelChairRepository;

@Service
public class WheelChairService {
	@Autowired
	private WheelChairRepository wheelChairRepo;

	public void UpdateStatus(Long id) {
		Wheel_Chair wc = wheelChairRepo.findById(id)
				.orElseThrow(()-> new ResourseNotFoundException("Invalid Wheel Chair ID"));

		wc.setWcStatus(!wc.getWcStatus());
		wheelChairRepo.save(wc);
	}
}
