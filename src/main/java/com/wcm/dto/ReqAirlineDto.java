package com.wcm.dto;

import org.springframework.stereotype.Component;

@Component
public class ReqAirlineDto {

	private Long id;
	private String name;
	private String airlineCode;
	private String opType;
	private Long fleet;
	private String username;
	private String password;

	public ReqAirlineDto() {

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

}
