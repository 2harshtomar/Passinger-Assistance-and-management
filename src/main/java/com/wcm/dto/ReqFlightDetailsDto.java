package com.wcm.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class ReqFlightDetailsDto {
	private String flightNo;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private String airCraftName;
	private String status;
	private String sourseTerminalNo;
	private String destinationTerminalNo;
	private String sourceSationName;
	private String sourceGateNo;
	private String destinationStationName;
	private String destinationGateNo;
	
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
	public String getSourceSationName() {
		return sourceSationName;
	}
	public void setSourceSationName(String sourceSationName) {
		this.sourceSationName = sourceSationName;
	}
	public String getDestinationStationName() {
		return destinationStationName;
	}
	public void setDestinationStationName(String destinationStationName) {
		this.destinationStationName = destinationStationName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSourceGateNo() {
		return sourceGateNo;
	}
	public void setSourceGateNo(String sourceGateNo) {
		this.sourceGateNo = sourceGateNo;
	}
	public String getDestinationGateNo() {
		return destinationGateNo;
	}
	public void setDestinationGateNo(String destinationGateNo) {
		this.destinationGateNo = destinationGateNo;
	}
	
	
}
