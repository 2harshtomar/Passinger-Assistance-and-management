package com.wcm.dto;

import org.springframework.stereotype.Component;

@Component
public class ResWheelchairDto {
	private Long id;
	private String wcCode;
	private boolean wcStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWcCode() {
		return wcCode;
	}
	public void setWcCode(String wcCode) {
		this.wcCode = wcCode;
	}
	public boolean isWcStatus() {
		return wcStatus;
	}
	public void setWcStatus(boolean wcStatus) {
		this.wcStatus = wcStatus;
	}
	@Override
	public String toString() {
		return "ResWheelchairDto [id=" + id + ", wcCode=" + wcCode + ", wcStatus=" + wcStatus + "]";
	}
	
	
	
}
