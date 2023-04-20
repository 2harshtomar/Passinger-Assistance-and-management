package com.wcm.dto;

import java.time.LocalDateTime;

public class ResFlightDetailsDto {
	private Long id;
	private String flightNo;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private String sourseTerminalNo;
	private String destinationTerminalNo;
	private String sourceStationName;
	private String destinationStationName;
	private String sStNumber;
	private String dStNumber;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getSourceStationName() {
		return sourceStationName;
	}
	public void setSourceStationName(String sourceStationName) {
		this.sourceStationName = sourceStationName;
	}
	public String getDestinationStationName() {
		return destinationStationName;
	}
	public void setDestinationStationName(String destinationStationName) {
		this.destinationStationName = destinationStationName;
	}
	public String getsStNumber() {
		return sStNumber;
	}
	public void setsStNumber(String sStNumber) {
		this.sStNumber = sStNumber;
	}
	public String getdStNumber() {
		return dStNumber;
	}
	public void setdStNumber(String dStNumber) {
		this.dStNumber = dStNumber;
	}
	
	
}
