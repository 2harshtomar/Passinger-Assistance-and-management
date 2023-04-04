package com.wcm.demo.model;

import java.time.LocalDateTime;

import com.wcm.demo.enums.SsrStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	@Enumerated(EnumType.STRING)
	private SsrStatusEnum status;
	@OneToOne
	private PassengerDetails pssengerDetails;
	
	@OneToOne
	private Staff staff;
	
	@OneToOne
	private Wheel_Chair wheelChair;
	
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
	
	public SsrStatusEnum getStatus() {
		return status;
	}
	public void setStatus(SsrStatusEnum status) {
		this.status = status;
	}
	public PassengerDetails getPssengerDetails() {
		return pssengerDetails;
	}
	public void setPssengerDetails(PassengerDetails pssengerDetails) {
		this.pssengerDetails = pssengerDetails;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Wheel_Chair getWheelChair() {
		return wheelChair;
	}
	public void setWheelChair(Wheel_Chair wheelChair) {
		this.wheelChair = wheelChair;
	}
	
	
}
