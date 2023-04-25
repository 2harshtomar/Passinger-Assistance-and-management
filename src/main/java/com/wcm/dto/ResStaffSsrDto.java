package com.wcm.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
@Component
public class ResStaffSsrDto {
	private Long id;
	private String pname;
	private String pcontact;
	private String StNumber;
	private Long sid;
	private Long did;
//	private String dStNumber;
	private String flightNo;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private String flStatus;
	private String ssrStatus;
	private String TerminalNo;
//	private String destTerminalNo;
	private boolean isArcived;
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcontact() {
		return pcontact;
	}
	public void setPcontact(String pcontact) {
		this.pcontact = pcontact;
	}
	public String getStNumber() {
		return StNumber;
	}
	public void setStNumber(String stNumber) {
		StNumber = stNumber;
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
	public String getFlStatus() {
		return flStatus;
	}
	public void setFlStatus(String flStatus) {
		this.flStatus = flStatus;
	}
	public String getSsrStatus() {
		return ssrStatus;
	}
	public void setSsrStatus(String ssrStatus) {
		this.ssrStatus = ssrStatus;
	}
	public String getTerminalNo() {
		return TerminalNo;
	}
	public void setTerminalNo(String terminalNo) {
		TerminalNo = terminalNo;
	}
	public boolean isArcived() {
		return isArcived;
	}
	public void setArcived(boolean isArcived) {
		this.isArcived = isArcived;
	}
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public Long getDid() {
		return did;
	}
	public void setDid(Long did) {
		this.did = did;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
