package com.wcm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.wcm.model.Staff;
import com.wcm.model.Wheel_Chair;
import com.wcm.repository.StaffRepository;
import com.wcm.repository.WheelChairRepository;
import com.wcm.utility.StaffWheelChairFactory;
import com.wcm.utility.WcmQueue;
import com.wcm.utility.WcmSet;
@Service
public class SationServiceMAA implements StaffWheelChairFactory {

	private Queue<Object> staffQueueMAA;
	private Queue<Object> wheelchairQueueMAA;
	private Set<Object> wheelchairSet;
	private Set<Object> staffSet;
	

	@Autowired
	private WcmQueue wcmQueue;
	
	@Autowired 
	private WcmSet wcmSet;
	
	@Autowired
	private StaffRepository staffRepo;
	
	@Autowired
	private WheelChairRepository wcRepo;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private WheelChairService wheelChairService;
	
	@EventListener(ApplicationReadyEvent.class)
	@Override
	public void createQueue() {
		this.staffQueueMAA = wcmQueue.createQueue();
		this.staffSet = wcmSet.createSet();
		this.wheelchairQueueMAA = wcmQueue.createQueue();
		this.wheelchairSet = wcmSet.createSet();
		System.out.println("QUEUE created");
	}
	
	//Staff QUEUE
	
	// method will get Available staff from db and puth them into queue
	@Scheduled(fixedDelay = 15000, initialDelay = 2000)
	public void updateStaffQueue() {
		this.staffSet = staffRepo.getStaffSet("MAA", "AVAILABLE");
		this.staffSet.removeAll(this.staffQueueMAA);
		this.staffQueueMAA.addAll(this.staffSet);
		this.staffSet.clear();
	}
	
	// get the first element of staff QUEUE
	@Override
	public Object getStaff() {
		return this.staffQueueMAA.remove();
	}
	
	//Wheel chair QUEUE
	
	// method will get Available staff from db and puth them into queue
	@Scheduled(fixedDelay = 15000, initialDelay = 2000)
	public void updateWheelChairQueue() {
		this.wheelchairSet = wcRepo.getWheelChairSet("MAA",true);
		this.wheelchairSet.removeAll(this.wheelchairQueueMAA);
		this.wheelchairQueueMAA.addAll(this.wheelchairSet);
		this.wheelchairSet.clear();
//		System.out.println("wc_hit");
	}
	
	// display queue's
	@Override
	public void displayQueue() {
		System.out.println("STAFF QUEUE DATA - "+this.staffQueueMAA.toString());
		System.out.println("WC QUEUE DATA - "+this.wheelchairQueueMAA.toString());
	}
	
	//get QUEUE status
	@Override
	public int getQueueStatus() {
		boolean staff = !staffQueueMAA.isEmpty();
		boolean wc = !wheelchairQueueMAA.isEmpty();
		if(staff && wc) { return 3;}
		else if(staff == true && wc == false) {return 2;}
		else if(staff == false && wc == true) {return 1;}
		else {return 0;}
	}
	
	// assigns queue and set to null;
	@Override
	public void DeleteQueue() {
		this.staffQueueMAA = null;
		this.staffSet = null;
		this.wcmQueue = null;
		this.wcmSet = null;
		System.out.println("Queue nullified");
	}

	@Override
	public Object getWheelChair() {
		return this.wheelchairQueueMAA.remove();
	}
	
	@Override
	public List<Object> getStaffWheelChairBasedOnCode(int code) {
		List<Object> wheelChairStaff = new ArrayList<>();
		Wheel_Chair wheelChair = new Wheel_Chair();
		Staff staff = new Staff();
		switch (code) {
		case 3: staff = (Staff) staffQueueMAA.remove();
				wheelChair = (Wheel_Chair) wheelchairQueueMAA.remove();
				staffService.updateStaffStatus(staff.getId());
				wheelChairService.UpdateStatus(wheelChair.getId());
				wheelChairStaff.add(staff);
				wheelChairStaff.add(wheelChair);
				return wheelChairStaff;
		case 2: staff = (Staff) staffQueueMAA.remove();
				staffService.updateStaffStatus(staff.getId());
				wheelChairStaff.add(staffQueueMAA.remove());
				return wheelChairStaff;
		case 1: wheelChair = (Wheel_Chair) wheelchairQueueMAA.remove();
				wheelChairService.UpdateStatus(wheelChair.getId());
				wheelChairStaff.add(wheelchairQueueMAA.remove());
				return wheelChairStaff;
	}
		return wheelChairStaff;
	
}
}
