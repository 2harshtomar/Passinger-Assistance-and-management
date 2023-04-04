package com.wcm.demo.model;

import com.wcm.demo.enums.StaffStatusEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String staffCode;
	@Enumerated(EnumType.STRING)
	private StaffStatusEnum status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public StaffStatusEnum getStatus() {
		return status;
	}
	public void setStatus(StaffStatusEnum status) {
		this.status = status;
	}
	
	
}
