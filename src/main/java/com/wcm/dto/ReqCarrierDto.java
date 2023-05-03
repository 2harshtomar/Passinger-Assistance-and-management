package com.wcm.dto;

import org.springframework.stereotype.Component;

@Component
public class ReqCarrierDto {
	private String name;
	private Long maxNoSeats;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMaxNoSeats() {
		return maxNoSeats;
	}
	public void setMaxNoSeats(Long maxNoSeats) {
		this.maxNoSeats = maxNoSeats;
	}
	
	
}
