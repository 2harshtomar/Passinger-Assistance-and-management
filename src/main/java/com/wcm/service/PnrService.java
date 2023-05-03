package com.wcm.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcm.dto.ReqPnrDto;
import com.wcm.model.Carrier;
import com.wcm.model.PassengerDetails;
import com.wcm.model.PassengerNameRecord;
import com.wcm.repository.CarrierRepository;
import com.wcm.repository.PnrRepository;

@Service
public class PnrService {
	
	@Autowired
	private CarrierRepository carrierRepository;
	
	@Autowired
	private PnrRepository pnrRepo;

	
	public String GeneratePNR(List<PassengerDetails> passengers) {
		
		String source = passengers.get(0).getFlightDetails().getSourceStation().getStNumber();
		int sepPos = source.lastIndexOf("-");
		source = source.substring(0,sepPos);
		
		String destination = passengers.get(0).getFlightDetails().getDestinationStation().getStNumber();
		sepPos = destination.lastIndexOf("-");
		destination = destination.substring(0, sepPos);
		
		 Random r = new Random();
	     int rnd  = r.nextInt((999 - 100) + 1) + 100;
	     String rndm = String.valueOf(rnd);
	     
	     return source + rndm + destination;
	}
	
	public String GetSeatsName(List<PassengerDetails> passengers, Long cid) {
		Optional<Carrier> optional = carrierRepository.findById(cid);
		Carrier carrier = optional.get();
		Long current = carrier.getSeatsOccupied();
		String seats = "";
		String prefix = "D";
		if(current >= 0 && current <=25) {
			prefix = "A";
		}
		else if(current > 25 && current <= 50) {
			prefix = "B";
		}
		else if(current > 50 && current <= 75) {
			prefix = "C";
		}
		
		for(int i=1;i<=passengers.size();i++) {
			seats = seats+ " " + prefix + String.valueOf(++current);
		}
		
		return seats;
	}
	
	public PassengerNameRecord addPnr(ReqPnrDto reqPnrDto){
		PassengerNameRecord pnr = new PassengerNameRecord();
		pnr.setPnrNo(GeneratePNR(reqPnrDto.getPassengers()));
		pnr.setPassengers(reqPnrDto.getPassengers());
		pnr.setSeats( GetSeatsName(reqPnrDto.getPassengers(), reqPnrDto.getPassengers().get(0).getFlightDetails().getCarrier().getId()));
		pnr.setNumberOfSeats(String.valueOf(reqPnrDto.getPassengers().size()));
		return pnrRepo.save(pnr);
	}
}
