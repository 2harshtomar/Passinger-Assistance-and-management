package com.wcm.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Airline {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String airlineCode;
	private String opType;
	private Long fleet;

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
	private List<Staff> staff;

	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
	private List<Wheel_Chair> wheel_chair;

	@OneToOne
	private User user;

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

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	public Long getFleet() {
		return fleet;
	}

	public void setFleet(Long fleet) {
		this.fleet = fleet;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public List<Staff> getStaff() {
		return staff;
	}

	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}

	public List<Wheel_Chair> getWheel_chair() {
		return wheel_chair;
	}

	public void setWheel_chair(List<Wheel_Chair> wheel_chair) {
		this.wheel_chair = wheel_chair;
	}

}
