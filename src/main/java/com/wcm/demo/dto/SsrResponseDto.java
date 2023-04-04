package com.wcm.demo.dto;

import java.time.LocalDateTime;

import com.wcm.demo.enums.SsrStatusEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public class SsrResponseDto {
	private Long id;
	private String pname;
	private String pcontcat;
	private LocalDateTime openDateTime;
	@Enumerated(EnumType.STRING)
	private SsrStatusEnum status;
	
	private String flightNo;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private String airCraftName;
	private String source;
	private String destination;
	
	//Getter and Setter
	
	
	public String getPname() {
		return pname;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public SsrStatusEnum getStatus() {
		return status;
	}
	public void setStatus(SsrStatusEnum status) {
		this.status = status;
	}
	public LocalDateTime getOpenDateTime() {
		return openDateTime;
	}
	public void setOpenDateTime(LocalDateTime openDateTime) {
		this.openDateTime = openDateTime;
	}
	
	
	
	
}
