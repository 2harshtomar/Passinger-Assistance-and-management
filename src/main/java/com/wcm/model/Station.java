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
public class Station {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String StNumber;
	private String name;
	private String location;
	private String type;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_id", referencedColumnName = "id")
	private List<Wheel_Chair> wheel_chair;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_id", referencedColumnName = "id")
	private List<Staff> staff;
	
	@OneToOne
	private User user;
	
	
	public String getStNumber() {
		return StNumber;
	}
	public void setStNumber(String stNumber) {
		this.StNumber = stNumber;
	}
	public List<Wheel_Chair> getWheel_chair() {
		return wheel_chair;
	}
	public void setWheel_chair(List<Wheel_Chair> wheel_chair) {
		this.wheel_chair = wheel_chair;
	}
	public List<Staff> getStaff() {
		return staff;
	}
	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getApNumber() {
		return StNumber;
	}
	public void setApNumber(String apNumber) {
		StNumber = apNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
