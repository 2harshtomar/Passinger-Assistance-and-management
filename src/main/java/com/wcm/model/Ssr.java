package com.wcm.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Ssr {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDateTime openDateTime;
	private LocalDateTime closeDateTime;
	private String status;// ACTIVE-PASENGER DEPARTED
	private boolean isArcived;

	@OneToOne
	private PassengerDetails pssengerDetails;

	@OneToOne
	private Staff sStaff;
	
	@OneToOne
	private Staff dStaff;

	@OneToOne
	private Wheel_Chair sWheelChair;
	
	@OneToOne
	private Wheel_Chair dWheelChair;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getOpenDateTime() {
		return openDateTime;
	}
	public void setOpenDateTime(LocalDateTime openDateTime) {
		this.openDateTime = openDateTime;
	}
	public LocalDateTime getCloseDateTime() {
		return closeDateTime;
	}
	public void setCloseDateTime(LocalDateTime closeDateTime) {
		this.closeDateTime = closeDateTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public PassengerDetails getPssengerDetails() {
		return pssengerDetails;
	}
	public void setPssengerDetails(PassengerDetails pssengerDetails) {
		this.pssengerDetails = pssengerDetails;
	}
	public Staff getsStaff() {
		return sStaff;
	}
	public void setsStaff(Staff sStaff) {
		this.sStaff = sStaff;
	}
	public Staff getdStaff() {
		return dStaff;
	}
	public void setdStaff(Staff dStaff) {
		this.dStaff = dStaff;
	}
	public Wheel_Chair getsWheelChair() {
		return sWheelChair;
	}
	public void setsWheelChair(Wheel_Chair sWheelChair) {
		this.sWheelChair = sWheelChair;
	}
	public Wheel_Chair getdWheelChair() {
		return dWheelChair;
	}
	public void setdWheelChair(Wheel_Chair dWheelChair) {
		this.dWheelChair = dWheelChair;
	}
	public boolean isArcived() {
		return isArcived;
	}
	public void setArcived(boolean isArcived) {
		this.isArcived = isArcived;
	}
	

}
