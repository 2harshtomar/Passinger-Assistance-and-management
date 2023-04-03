package com.wcm.demo.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wcm.demo.model.Staff;
import com.wcm.demo.model.Wheel_Chair;
@Component
public class ResStationDto {
	private Long id;
	private String StNumber;
	private String name;
	private String location;
	private String type;
	private String username;
	private List<Staff> staff;
	private List<Wheel_Chair> wheelChair;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStNumber() {
		return StNumber;
	}
	public void setStNumber(String stNumber) {
		StNumber = stNumber;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<Staff> getStaff() {
		return staff;
	}
	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}
	public List<Wheel_Chair> getWheelChair() {
		return wheelChair;
	}
	public void setWheelChair(List<Wheel_Chair> wheelChair) {
		this.wheelChair = wheelChair;
	}
	
	
}
