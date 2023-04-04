package com.wcm.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Wheel_Chair {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	
}
