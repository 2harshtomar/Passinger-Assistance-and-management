package com.wcm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcm.model.Airline;
import com.wcm.model.Station;
import com.wcm.repository.AirlineRepository;
import com.wcm.repository.StationRepository;
import com.wcm.utility.StaffWheelChairFactory;

@Service
public class StationRouterService {
	@Autowired
	private StationServiceDL stationServiceDL;
	
	@Autowired
	private SationServiceMAA stationServiceMAA;
	
	@Autowired
	private StationRepository stationRepo;
	
	@Autowired
	private AirlineRepository airlineRepo;
	
	public StaffWheelChairFactory ForwardRequest(String code) {
		int sepPos = code.lastIndexOf("-");
	    String stcode = code.substring(0,sepPos);
		
		switch (stcode) {
		case "MAA":
				return stationServiceMAA;
		case "DL":
			return stationServiceDL;

		default:
			return null;
		}
	}
	
	public String getEntity(long id) {
		String code = "";
		try {
			Station station = stationRepo.getStationByUserId(id);
			code = station.getStNumber();
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		try {
			Airline airline = airlineRepo.getAirlineByUserId(id); 
			code = airline.getAirlineCode();
		} 
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
//		if(!optional.isEmpty()) {
//			Station station = optional.get();
//			code = station.getStNumber();
//			int sepPos = code.lastIndexOf("-");
//		    code =code.substring(0,sepPos);
//		}
//		Optional<Airline> optional2 = airlineRepo.findById(id);
//		if(!optional.isEmpty()) {
//			Airline airline = optional2.get();
//			code = airline.getAirlineCode();
//			int sepPos = code.lastIndexOf("-");
//		    code  =code.substring(0,sepPos);
//		}
		int sepPos = code.lastIndexOf("-");
	    code =code.substring(0,sepPos);
		return code;
	}
}
