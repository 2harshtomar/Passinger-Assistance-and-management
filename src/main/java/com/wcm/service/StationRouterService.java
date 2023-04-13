package com.wcm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcm.utility.StaffWheelChairFactory;

@Service
public class StationRouterService {
	@Autowired
	private StationServiceDL stationServiceDL;
	
	@Autowired
	private SationServiceMAA stationServiceMAA;
	
	public StaffWheelChairFactory ForwardRequest(String code) {
		
		switch (code) {
		case "MAA":
				return stationServiceMAA;
		case "DL":
			return stationServiceDL;

		default:
			return null;
		}
	}
}
