package com.wcm.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class PassengerNameRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String pnrNo;
	private String seats;
	private String numberOfSeats;
	
	@OneToMany
	private List<PassengerDetails> passengers;
	
	@OneToOne
	private Ssr ssr;
	
	public String getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(String numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<PassengerDetails> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerDetails> passengers) {
		this.passengers = passengers;
	}
	public Ssr getSsr() {
		return ssr;
	}
	public void setSsr(Ssr ssr) {
		this.ssr = ssr;
	}
	
}
