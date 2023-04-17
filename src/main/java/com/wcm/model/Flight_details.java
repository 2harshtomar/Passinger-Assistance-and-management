package com.wcm.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Flight_details {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String flightNo;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private String airCraftName;
	private String status; // boarding NA, boarding , departed, arrived 
	private String sourseTerminalNo;
	private String destinationTerminalNo;


	@OneToMany(cascade = CascadeType.ALL)
	private List<PassengerDetails> passenger;
	
	@OneToOne
	private Station sourceStation;
	
	@OneToOne
	private Station destinationStation;
	
	@OneToOne
	private Airline airline;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public LocalDateTime getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(LocalDateTime fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public LocalDateTime getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(LocalDateTime toDateTime) {
		this.toDateTime = toDateTime;
	}

	public String getAirCraftName() {
		return airCraftName;
	}

	public void setAirCraftName(String airCraftName) {
		this.airCraftName = airCraftName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSourseTerminalNo() {
		return sourseTerminalNo;
	}

	public void setSourseTerminalNo(String sourseTerminalNo) {
		this.sourseTerminalNo = sourseTerminalNo;
	}

	public String getDestinationTerminalNo() {
		return destinationTerminalNo;
	}

	public void setDestinationTerminalNo(String destinationTerminalNo) {
		this.destinationTerminalNo = destinationTerminalNo;
	}

	public List<PassengerDetails> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<PassengerDetails> passenger) {
		this.passenger = passenger;
	}

	public Station getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(Station sourceStation) {
		this.sourceStation = sourceStation;
	}

	public Station getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(Station destinationStation) {
		this.destinationStation = destinationStation;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	
	

}
