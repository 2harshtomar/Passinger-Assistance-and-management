package com.wcm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Carrier {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Long maxNoSeats;
	private Long SeatsOccupied;
	private String status; // AVAILABLE, IN-FLIGHT, GROUNDED
	
	@OneToOne
	private Airline airline;
	
	public Carrier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Carrier(Long id, String name, Long maxNoSeats, Long seatsOccupied) {
		super();
		this.id = id;
		this.name = name;
		this.maxNoSeats = maxNoSeats;
		SeatsOccupied = seatsOccupied;
	}
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
	public Airline getAirline() {
		return airline;
	}
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
