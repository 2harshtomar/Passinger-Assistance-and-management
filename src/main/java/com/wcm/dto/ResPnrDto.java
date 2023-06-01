package com.wcm.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ResPnrDto {
	private List<ResPassengerDetailsFlightDto> passengers;
	private String flightNo;
	private String fromDateTime;
	private String toDateTime;
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
	private String sourceGateNo;
	private String destinationGateNo;
	private String seats;
	private String numberOfSeats;
	private String pnr;
	private String sStaffname;
	private String sStaffContact;
	private String dStaffname;
	private String dStaffContact;
	
	
	public ResPnrDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public ResPnrDto(List<ResPassengerDetailsFlightDto> passengers, String flightNo, String fromDateTime,
			String toDateTime, String airCraftName, String status, String sourseTerminalNo,
			String destinationTerminalNo, String sourceStationNumber, String sourceStationName,
			String sourceStationLocation, String destStationNumber, String destStationName, String destStationLocation,
			String airlineName, String airlineCode, String sourceGateNo, String destinationGateNo, String seats,
			String numberOfSeats, String pnr, String sStaffname, String sStaffContact, String dStaffname,
			String dStaffContact) {
		super();
		this.passengers = passengers;
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
		this.sourceGateNo = sourceGateNo;
		this.destinationGateNo = destinationGateNo;
		this.seats = seats;
		this.numberOfSeats = numberOfSeats;
		this.pnr = pnr;
		this.sStaffname = sStaffname;
		this.sStaffContact = sStaffContact;
		this.dStaffname = dStaffname;
		this.dStaffContact = dStaffContact;
	}


	public String getFlightNo() {
		return flightNo;
	}


	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}


	public String getFromDateTime() {
		return fromDateTime;
	}


	public void setFromDateTime(String fromDateTime) {
		this.fromDateTime = fromDateTime;
	}


	public String getToDateTime() {
		return toDateTime;
	}


	public void setToDateTime(String toDateTime) {
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


	public String getSeats() {
		return seats;
	}


	public void setSeats(String seats) {
		this.seats = seats;
	}


	public String getNumberOfSeats() {
		return numberOfSeats;
	}


	public void setNumberOfSeats(String numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}


	public String getPnr() {
		return pnr;
	}


	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public List<ResPassengerDetailsFlightDto> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<ResPassengerDetailsFlightDto> passengers) {
		this.passengers = passengers;
	}

	public String getsStaffname() {
		return sStaffname;
	}

	public void setsStaffname(String sStaffname) {
		this.sStaffname = sStaffname;
	}

	public String getsStaffContact() {
		return sStaffContact;
	}

	public void setsStaffContact(String sStaffContact) {
		this.sStaffContact = sStaffContact;
	}

	public String getdStaffname() {
		return dStaffname;
	}

	public void setdStaffname(String dStaffname) {
		this.dStaffname = dStaffname;
	}

	public String getdStaffContact() {
		return dStaffContact;
	}

	public void setdStaffContact(String dStaffContact) {
		this.dStaffContact = dStaffContact;
	}
	
}
