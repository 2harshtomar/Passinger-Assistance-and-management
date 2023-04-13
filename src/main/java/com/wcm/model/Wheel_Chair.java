package com.wcm.model;

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
	public boolean getWcStatus() {
		return wcStatus;
	}
	public void setWcStatus(boolean wcStatus) {
		this.wcStatus = wcStatus;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id.intValue();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Wheel_Chair wheelChair = (Wheel_Chair) obj;
		return id == wheelChair.id;
	}
	@Override
	public String toString() {
		return "Wheel_Chair [id=" + id + ", wcCode=" + wcCode + ", wcStatus=" + wcStatus + "]";
	}
	
	

}
