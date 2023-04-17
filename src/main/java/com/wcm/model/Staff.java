package com.wcm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String contact;
	private String email;
	private String staffCode;
	private String status;
	
	

	public Staff() {
		
	}
	
	public Staff(String staffCode) {
		this.staffCode = staffCode;
	}
	
	@OneToOne
	private User user;

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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
		
		Staff staff = (Staff) obj;
		return id == staff.id;
	}
	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", contact=" + contact + ", email=" + email + ", staffCode="
				+ staffCode + ", status=" + status + ", user=" + user + "]";
	}
	
	
}
