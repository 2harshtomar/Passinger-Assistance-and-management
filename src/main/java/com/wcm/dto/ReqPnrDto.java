package com.wcm.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wcm.model.PassengerDetails;

@Component
public class ReqPnrDto {
	
	private List<PassengerDetails> passengers;
	

	public ReqPnrDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<PassengerDetails> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<PassengerDetails> passengers) {
		this.passengers = passengers;
	}
	
	
}
