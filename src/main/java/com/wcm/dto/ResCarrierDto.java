package com.wcm.dto;

import org.springframework.stereotype.Component;

@Component
public class ResCarrierDto {
	private Long id;
	private String name;
	private Long maxNoSeats;
	private Long SeatsOccupied;
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Long getSeatsOccupied() {
		return SeatsOccupied;
	}
	public void setSeatsOccupied(Long seatsOccupied) {
		SeatsOccupied = seatsOccupied;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	} 
	
	
}
