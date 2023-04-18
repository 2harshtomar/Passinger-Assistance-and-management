package com.wcm.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class ResPassengerDetailsDto {
	
//	private Long id;
	private String name;
	private String contact;
	private String address;
	private String flightNo;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private String airCraftName;
	private String status; // boarding NA, boarding , departed, arrived 
	private String sourseTerminalNo;
	private String destinationTerminalNo;
	private String sourceStationNumber;
	private String sourceStationName;
	private String sourceStationLocation;
	private String destStationNumber;
	private String destStationName;
	private String destStationLocation;
	private String airlineName;
	private String airlineCode;
	
	public ResPassengerDetailsDto() {
		
	}

	public ResPassengerDetailsDto(Long id, String name, String contact, String address, String flightNo,
			LocalDateTime fromDateTime, LocalDateTime toDateTime, String airCraftName, String status,
			String sourseTerminalNo, String destinationTerminalNo, String sourceStationNumber, String sourceStationName,
			String sourceStationLocation, String destStationNumber, String destStationName, String destStationLocation,
			String airlineName, String airlineCode) {
		super();
//		this.id = id;
		this.name = name;
		this.contact = contact;
		this.address = address;
		this.flightNo = flightNo;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.airCraftName = airCraftName;
		this.status = status;
		this.sourseTerminalNo = sourseTerminalNo;
		this.destinationTerminalNo = destinationTerminalNo;
		this.sourceStationNumber = sourceStationNumber;
		this.sourceStationName = sourceStationName;
		this.sourceStationLocation = sourceStationLocation;
		this.destStationNumber = destStationNumber;
		this.destStationName = destStationName;
		this.destStationLocation = destStationLocation;
		this.airlineName = airlineName;
		this.airlineCode = airlineCode;
	}



//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	

	public String getSourceStationNumber() {
		return sourceStationNumber;
	}

	public void setSourceStationNumber(String sourceStationNumber) {
		this.sourceStationNumber = sourceStationNumber;
	}

	public String getSourceStationName() {
		return sourceStationName;
	}

	public void setSourceStationName(String sourceStationName) {
		this.sourceStationName = sourceStationName;
	}

	public String getSourceStationLocation() {
		return sourceStationLocation;
	}

	public void setSourceStationLocation(String sourceStationLocation) {
		this.sourceStationLocation = sourceStationLocation;
	}

	public String getDestStationNumber() {
		return destStationNumber;
	}

	public void setDestStationNumber(String destStationNumber) {
		this.destStationNumber = destStationNumber;
	}

	public String getDestStationName() {
		return destStationName;
	}

	public void setDestStationName(String destStationName) {
		this.destStationName = destStationName;
	}

	public String getDestStationLocation() {
		return destStationLocation;
	}

	public void setDestStationLocation(String destStationLocation) {
		this.destStationLocation = destStationLocation;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}

	@Override
	public String toString() {
		return "ResPassengerDetailsDto [name=" + name + ", contact=" + contact + ", address=" + address
				+ ", flightNo=" + flightNo + ", fromDateTime=" + fromDateTime + ", toDateTime=" + toDateTime
				+ ", airCraftName=" + airCraftName + ", status=" + status + ", sourseTerminalNo=" + sourseTerminalNo
				+ ", destinationTerminalNo=" + destinationTerminalNo + ", sourceStationNumber=" + sourceStationNumber
				+ ", sourceStationName=" + sourceStationName + ", sourceStationLocation=" + sourceStationLocation
				+ ", destStationNumber=" + destStationNumber + ", destStationName=" + destStationName
				+ ", destStationLocation=" + destStationLocation + ", airlineName=" + airlineName + ", airlineCode="
				+ airlineCode + "]";
	}

	
}
