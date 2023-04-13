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

import com.wcm.repository.StaffRepository;
import com.wcm.repository.WheelChairRepository;
import com.wcm.utility.StaffWheelChairFactory;
import com.wcm.utility.WcmQueue;
import com.wcm.utility.WcmSet;
@Service
public class SationServiceMAA implements StaffWheelChairFactory {

	private Queue<Object> staffQueue;
	private Queue<Object> wheelchairQueue;
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
	
	@EventListener(ApplicationReadyEvent.class)
	@Override
	public void createQueue() {
		this.staffQueue = wcmQueue.createQueue();
		this.staffSet = wcmSet.createSet();
		this.wheelchairQueue = wcmQueue.createQueue();
		this.wheelchairSet = wcmSet.createSet();
		System.out.println("QUEUE created");
	}
	
	//Staff QUEUE
	
	// method will get Available staff from db and puth them into queue
	@Scheduled(fixedDelay = 15000, initialDelay = 2000)
	public void updateStaffQueue() {
		this.staffSet = staffRepo.getStaffSet("MAA", "AVAILABLE");
		this.staffSet.removeAll(this.staffQueue);
		this.staffQueue.addAll(this.staffSet);
		this.staffSet.clear();
	}
	
	// get the first element of staff QUEUE
	@Override
	public Object getStaff() {
		return this.staffQueue.remove();
	}
	
	//Wheel chair QUEUE
	
	// method will get Available staff from db and puth them into queue
	@Scheduled(fixedDelay = 15000, initialDelay = 2000)
	public void updateWheelChairQueue() {
		this.wheelchairSet = wcRepo.getWheelChairSet("MAA",true);
		this.wheelchairSet.removeAll(this.wheelchairQueue);
		this.wheelchairQueue.addAll(this.wheelchairSet);
		this.wheelchairSet.clear();
		System.out.println("wc_hit");
	}
	
	// display queue's
	@Override
	public void displayQueue() {
		System.out.println("STAFF QUEUE DATA - "+this.staffQueue.toString());
		System.out.println("WC QUEUE DATA - "+this.wheelchairQueue.toString());
	}
	
	//get QUEUE status
	@Override
	public int getQueueStatus() {
		boolean staff = !staffQueue.isEmpty();
		boolean wc = !wheelchairQueue.isEmpty();
		if(staff && wc) { return 3;}
		else if(staff == true && wc == false) {return 2;}
		else if(staff == false && wc == true) {return 1;}
		else {return 0;}
	}
	
	// assigns queue and set to null;
	@Override
	public void DeleteQueue() {
		this.staffQueue = null;
		this.staffSet = null;
		this.wcmQueue = null;
		this.wcmSet = null;
		System.out.println("Queue nullified");
	}

	@Override
	public Object getWheelChair() {
		return this.wheelchairQueue.remove();
	}
	
	@Override
	public List<Object> getStaffWheelChairBasedOnCode(int code) {
		List<Object> wheelChairStaff = new ArrayList<>();
		switch (code) {
		case 3: wheelChairStaff.add(staffQueue.remove());
				wheelChairStaff.add(wheelchairQueue.remove());
				return wheelChairStaff;
		case 2: wheelChairStaff.add(staffQueue.remove());
				return wheelChairStaff;
		case 1: wheelChairStaff.add(wheelchairQueue.remove());
				return wheelChairStaff;
	}
		return wheelChairStaff;
	
}
}
