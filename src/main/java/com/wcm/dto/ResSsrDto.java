package com.wcm.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class ResSsrDto {
	private String pname;
	private String pcontact;
	private String sStNumber;
	private String dStNumber;
	private String flightNo;
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;
	private String flStatus;
	private String ssrStatus;
	private String sourseTerminalNo;
	private String destTerminalNo;
	private boolean isArcived;
	private Long sid;
	private Long did;
	
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
	public String getSourseTerminalNo() {
		return sourseTerminalNo;
	}
	public void setSourseTerminalNo(String sourseTerminalNo) {
		this.sourseTerminalNo = sourseTerminalNo;
	}
	public String getDestTerminalNo() {
		return destTerminalNo;
	}
	public void setDestTerminalNo(String destTerminalNo) {
		this.destTerminalNo = destTerminalNo;
	}
	public boolean getIsArcived() {
		return isArcived;
	}
	public void setIsArcived(boolean b) {
		this.isArcived = b;
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
	
	
}
