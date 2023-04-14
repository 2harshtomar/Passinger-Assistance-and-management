package com.wcm.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
@Component
public class ResSsrDto {
	private Long id;
	private String pname;
	private String pcontcat;
	private LocalDateTime openDateTime;
	private String status;
	
	private String flightNo;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private String airCraftName;
	private String source;
	private String destination;
	

	//Getter and Setter
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcontcat() {
		return pcontcat;
	}
	public void setPcontcat(String pcontcat) {
		this.pcontcat = pcontcat;
	}
	public LocalDateTime getOpenDateTime() {
		return openDateTime;
	}
	public void setOpenDateTime(LocalDateTime openDateTime) {
		this.openDateTime = openDateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
	
	
	
}
