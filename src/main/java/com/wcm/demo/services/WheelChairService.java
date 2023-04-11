package com.wcm.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.wcm.demo.dto.WheelChairResponseDto;
import com.wcm.demo.model.Wheel_Chair;
import com.wcm.demo.repository.WheelChairRepository;



@Service
public class WheelChairService {
	
	@Autowired
	private static  WheelChairRepository wcr;

	public static  List<WheelChairResponseDto> getAllWheelChair() {
		List<Wheel_Chair> list = wcr.findAll();
		List<WheelChairResponseDto> listDto = new ArrayList<>();
		for(Wheel_Chair w : list) {
			WheelChairResponseDto wcd = new WheelChairResponseDto();
			wcd.setId(w.getId());
			wcd.setWcCode(w.getWcCode());
			wcd.setWcStatus(true);
			listDto.add(wcd);
		}
		return listDto;
	}
	

	public static ResponseEntity<String> AddAllWheelChair(Wheel_Chair wc,String wcCode) {
		wc.setWcCode(wcCode);
		wc.setWcStatus(true);
		wc = wcr.save(wc);
		return ResponseEntity.status(HttpStatus.OK).body("Wheel Chair Added Sucessfully .");
	}
	
}
