package com.wcm.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wcm.model.Staff;
import com.wcm.model.Wheel_Chair;

@Component
public class ResAirlineDto {

	private Long id;
	private String name;
	private String AirlineCode;
	private String opType;
	private Long fleet;
	private Long userId;
	private String username;
	private List<Staff> staff;
	private List<Wheel_Chair> wheel_chair;

	public ResAirlineDto() {

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public List<Wheel_Chair> getWheel_chair() {
		return wheel_chair;
	}

	public void setWheel_chair(List<Wheel_Chair> wheel_chair) {
		this.wheel_chair = wheel_chair;
	}

}
