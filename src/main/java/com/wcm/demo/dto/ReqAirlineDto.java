package com.wcm.demo.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wcm.demo.model.Staff;
import com.wcm.demo.model.Wheel_Chair;

@Component
public class ReqAirlineDto {
	
	private Long id;
	private String AirlineCode;
	private String opType;
	private Long fleet;
	private String username;
	private String password;
	private List<Staff> staff;
	private List<Wheel_Chair> wheel_chair;
	
	public ReqAirlineDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAirlineCode() {
		return AirlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		AirlineCode = airlineCode;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
