package com.wcm.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wcm.dto.ReqPnrDto;
import com.wcm.dto.ResPassengerDetailsFlightDto;
import com.wcm.dto.ResPnrDto;
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
	
	@Autowired
	private ResPnrDto resPnrDto;

	
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
	
	public ResPnrDto convertToDto(PassengerNameRecord pnr) {
		resPnrDto.setPnr(pnr.getPnrNo());
		List<PassengerDetails> passengers = pnr.getPassengers();
		List<ResPassengerDetailsFlightDto> dtoPassengers = new ArrayList<>();
		for(PassengerDetails p:passengers) {
			ResPassengerDetailsFlightDto dto = new ResPassengerDetailsFlightDto();
			dto.setAddress(p.getAddress());
			dto.setName(p.getName());
			dto.setContact(p.getContact());
			dto.setId(p.getId());
			dtoPassengers.add(dto);
		}
		// data formatting - station code MMA-01 to MAA
		int ssepPos = pnr.getPassengers().get(0).getFlightDetails().getSourceStation().getStNumber().lastIndexOf("-");
		int dsepPos = pnr.getPassengers().get(0).getFlightDetails().getDestinationStation().getStNumber().lastIndexOf("-");
	    String sstcode = pnr.getPassengers().get(0).getFlightDetails().getSourceStation().getStNumber().substring(0,ssepPos);
	    String dstcode = pnr.getPassengers().get(0).getFlightDetails().getDestinationStation().getStNumber().substring(0,dsepPos);
	    // data formatting - date time to E dd-MMM-yyyy hh:mm
        LocalDateTime from = pnr.getPassengers().get(0).getFlightDetails().getFromDateTime();
        LocalDateTime to = pnr.getPassengers().get(0).getFlightDetails().getToDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E dd-MMM-yyyy hh:mm");
        String fromdate = from.format(formatter);
        String todate = to.format(formatter);
		resPnrDto.setPassengers(dtoPassengers);
		resPnrDto.setAirCraftName(pnr.getPassengers().get(0).getFlightDetails().getCarrier().getName());
		resPnrDto.setAirlineCode(pnr.getPassengers().get(0).getFlightDetails().getAirline().getAirlineCode());
		resPnrDto.setAirlineName(pnr.getPassengers().get(0).getFlightDetails().getAirline().getName());
		resPnrDto.setDestinationGateNo(pnr.getPassengers().get(0).getFlightDetails().getDestinationGateNo());
		resPnrDto.setDestinationTerminalNo(pnr.getPassengers().get(0).getFlightDetails().getDestinationTerminalNo());
		resPnrDto.setDestStationLocation(pnr.getPassengers().get(0).getFlightDetails().getDestinationStation().getLocation());
		resPnrDto.setDestStationName(pnr.getPassengers().get(0).getFlightDetails().getDestinationStation().getName());
		resPnrDto.setDestStationNumber(dstcode);
		resPnrDto.setFlightNo(pnr.getPassengers().get(0).getFlightDetails().getFlightNo());
		resPnrDto.setFromDateTime(fromdate);
		resPnrDto.setToDateTime(todate);
		resPnrDto.setNumberOfSeats(pnr.getNumberOfSeats());
		resPnrDto.setSeats(pnr.getSeats());
		resPnrDto.setSourceGateNo(pnr.getPassengers().get(0).getFlightDetails().getSourceGateNo());
		resPnrDto.setSourseTerminalNo(pnr.getPassengers().get(0).getFlightDetails().getSourseTerminalNo());
		resPnrDto.setSourceStationLocation(pnr.getPassengers().get(0).getFlightDetails().getSourceStation().getLocation());
		resPnrDto.setSourceStationName(pnr.getPassengers().get(0).getFlightDetails().getSourceStation().getName());
		resPnrDto.setSourceStationNumber(sstcode);
		resPnrDto.setStatus(pnr.getPassengers().get(0).getFlightDetails().getStatus());
		resPnrDto.setsStaffname(pnr.getSsr().getsStaff().getName());
		resPnrDto.setsStaffContact(pnr.getSsr().getsStaff().getContact());
		resPnrDto.setdStaffname(pnr.getSsr().getdStaff().getName());
		resPnrDto.setdStaffContact(pnr.getSsr().getdStaff().getContact());
		return resPnrDto;
		
	}
}
