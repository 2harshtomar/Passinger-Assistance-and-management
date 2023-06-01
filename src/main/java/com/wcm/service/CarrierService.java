package com.wcm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wcm.dto.ResCarrierDto;
import com.wcm.dto.ResponseDto;
import com.wcm.model.Carrier;
import com.wcm.model.PassengerDetails;
import com.wcm.repository.CarrierRepository;

@Service
public class CarrierService {
	
	@Autowired
	private CarrierRepository carrierRepository;
	
	@Autowired
	private ResponseDto responseDto;

	
	public ResponseEntity<Object> updateSeats(Long id, List<PassengerDetails> passengers){
		Optional<Carrier> optional = carrierRepository.findById(id);
		if(optional.isEmpty()) {
			responseDto.setMessage("Invalid Airline ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}
		Carrier carrier = optional.get();
		carrier.setSeatsOccupied(carrier.getSeatsOccupied()+passengers.size());
		carrierRepository.save(carrier);
		
		responseDto.setMessage("Seats Occupency updated");
		
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		
	}
	
	public ResCarrierDto convertToDto(Carrier carrier) {
		ResCarrierDto Dto = new ResCarrierDto();
		Dto.setId(carrier.getId());
		Dto.setMaxNoSeats(carrier.getMaxNoSeats());
		Dto.setName(carrier.getName());
		Dto.setSeatsOccupied(carrier.getSeatsOccupied());
		Dto.setStatus(carrier.getStatus());
		
		return Dto;
	}
	
}
