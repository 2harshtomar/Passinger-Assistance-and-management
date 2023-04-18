package com.wcm.dto;

import org.springframework.stereotype.Component;

@Component
public class PassengerResSsrDto {
	private String staffName;
	private String staffContact;
	private String terminalNo;
	private String airlineName;
	private boolean isArcived;
	
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffContact() {
		return staffContact;
	}
	public void setStaffContact(String staffContact) {
		this.staffContact = staffContact;
	}
	public String getTerminalNo() {
		return terminalNo;
	}
	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public boolean isArcived() {
		return isArcived;
	}
	public void setArcived(boolean isArcived) {
		this.isArcived = isArcived;
	}
	
	
}
