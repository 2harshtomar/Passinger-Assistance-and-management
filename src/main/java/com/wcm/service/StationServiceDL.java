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
public class StationServiceDL implements StaffWheelChairFactory {
	private Queue<Object> staffQueueDL;
	private Queue<Object> wheelchairQueueDL;
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
		this.staffQueueDL = wcmQueue.createQueue();
		this.staffSet = wcmSet.createSet();
		this.wheelchairQueueDL = wcmQueue.createQueue();
		this.wheelchairSet = wcmSet.createSet();
	}
	
	//Staff QUEUE
	
	// method will get Available staff from db and puth them into queue
	@Scheduled(fixedDelay = 15000, initialDelay = 2000)
	public void updateStaffQueue() {
		this.staffSet = staffRepo.getStaffSet("DL", "AVAILABLE");
		this.staffSet.removeAll(this.staffQueueDL);
		this.staffQueueDL.addAll(this.staffSet);
		this.staffSet.clear();
	}
	
	// get the status of the queue
	@Override
	public int getQueueStatus() {
		boolean staff = !staffQueueDL.isEmpty();
		boolean wc = !wheelchairQueueDL.isEmpty();
		if(staff && wc) { return 3;}
		else if(staff == true && wc == false) {return 2;}
		else if(staff == false && wc == true) {return 1;}
		else {return 0;}
	}
	
	// get the first element of staff QUEUE
	@Override
	public Object getStaff() {
		return this.staffQueueDL.remove();
	}
	
	//Wheel chair QUEUE
	
	// method will get Available staff from db and puth them into queue
	@Scheduled(fixedDelay = 15000, initialDelay = 2000)
	public void updateWheelChairQueue() {
		this.wheelchairSet = wcRepo.getWheelChairSet("DL",true);
		this.wheelchairSet.removeAll(this.wheelchairQueueDL);
		this.wheelchairQueueDL.addAll(this.wheelchairSet);
		this.wheelchairSet.clear();
//		System.out.println("wc_hit");
	}
	
	// get the first element of wheel chair QUEUE
	@Override
	public Object getWheelChair() {
		return this.wheelchairQueueDL.remove();
	}
	
	// display queue's
	@Override
	public void displayQueue() {
		System.out.println("STAFF QUEUE DATA - "+this.staffQueueDL.toString());
		System.out.println("WC QUEUE DATA - "+this.wheelchairQueueDL.toString());
	}
	
	// assigns queue and set to null;
	@Override
	public void DeleteQueue() {
		this.staffQueueDL = null;
		this.staffSet = null;
		System.out.println("Queue nullified");
	}
	
	@Override
	public List<Object> getStaffWheelChairBasedOnCode(int code) {
		List<Object> wheelChairStaff = new ArrayList<>();
		Wheel_Chair wheelChair = new Wheel_Chair();
		Staff staff = new Staff();
		switch (code) {
		case 3: 
				staff = (Staff) staffQueueDL.remove();
				staffService.updateStaffStatus(staff.getId());
				wheelChair = (Wheel_Chair) wheelchairQueueDL.remove();
				wheelChairService.UpdateStatus(wheelChair.getId());
				wheelChairStaff.add(staff);
				wheelChairStaff.add(wheelChair);
				return wheelChairStaff;
		case 2: staff = (Staff) staffQueueDL.remove();
				staffService.updateStaffStatus(staff.getId());
				wheelChairStaff.add(staffQueueDL.remove());
				return wheelChairStaff;
		case 1: wheelChair = (Wheel_Chair) wheelchairQueueDL.remove();
				wheelChairService.UpdateStatus(wheelChair.getId());
				wheelChairStaff.add(wheelchairQueueDL.remove());
				return wheelChairStaff;
	}
		return wheelChairStaff;
	
}
}
