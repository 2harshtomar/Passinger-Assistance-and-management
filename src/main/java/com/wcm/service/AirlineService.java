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
public class AirlineService {
	private Queue<Object> staffQueueAL;
	private Queue<Object> wheelchairQueueAL;
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
	private StationRouterService stationRouterService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private WheelChairService wheelChairService;
	
	@EventListener(ApplicationReadyEvent.class)
	public void createQueue() {
		this.staffQueueAL = wcmQueue.createQueue();
		this.staffSet = wcmSet.createSet();
		this.wheelchairQueueAL = wcmQueue.createQueue();
		this.wheelchairSet = wcmSet.createSet();
		// status message
		System.out.println("AI Queue created");
		System.out.println("AI Set created");
	}
	
	//Staff QUEUE
	
	// method will get Available staff from db and puth them into queue
	@Scheduled(fixedDelay = 15000, initialDelay = 2000)
	public void updateStaffQueue() {
		this.staffSet = staffRepo.getStaffSet("AI", "AVAILABLE");
		this.staffSet.removeAll(this.staffQueueAL);
		this.staffQueueAL.addAll(this.staffSet);
		this.staffSet.clear();
//		System.out.println("AI_staff_hit");
	}
	
	// get the status of the queue
	public boolean getQueueStatus() {
		return this.staffQueueAL.isEmpty();
	}
	
	// get the first element of staff QUEUE
	public Object getStaff() {
		return this.staffQueueAL.remove();
	}
	
	//Wheel chair QUEUE
	
	// method will get Available staff from db and puth them into queue
	@Scheduled(fixedDelay = 15000, initialDelay = 2000)
	public void updateWheelChairQueue() {
		this.wheelchairSet = wcRepo.getWheelChairSet("AI",true);
		this.wheelchairSet.removeAll(this.wheelchairQueueAL);
		this.wheelchairQueueAL.addAll(this.wheelchairSet);
		this.wheelchairSet.clear();
//		System.out.println("AI_wc_hit");
	}
	
	// get the first element of wheel chair QUEUE
	public Object getWheelChair() {
		return this.wheelchairQueueAL.remove();
	}
	
	// display queue's
	public void displayQueue() {
		System.out.println("AI_STAFF QUEUE DATA - "+this.staffQueueAL.toString());
		System.out.println("AI_WC QUEUE DATA - "+this.wheelchairQueueAL.toString());
	}
	
	// assigns queue and set to null;
	public void DeleteQueue() {
		this.staffQueueAL = null;
		this.staffSet = null;
		this.wcmQueue = null;
		this.wcmSet = null;
		System.out.println("Queue nullified");
	}
	
	// Station and Airline 
	public List<Object> RequestStation(String stCode) {
		StaffWheelChairFactory staffWheelChairFactory = stationRouterService.ForwardRequest(stCode);
		int statusCode = staffWheelChairFactory.getQueueStatus();
		List<Object> pair = new ArrayList<>();
		Staff staff = new Staff();
		Wheel_Chair wheelChair = new Wheel_Chair();
		switch (statusCode) {
		case 3:
			pair = staffWheelChairFactory.getStaffWheelChairBasedOnCode(statusCode);
			break;
		case 2: wheelChair = (Wheel_Chair) wheelchairQueueAL.remove();
				wheelChairService.UpdateStatus(wheelChair.getId());
				pair = staffWheelChairFactory.getStaffWheelChairBasedOnCode(statusCode);
				pair.add(wheelchairQueueAL.remove());
				break;
		case 1: staff = (Staff) staffQueueAL.remove();
				staffService.updateStaffStatus(staff.getId());
				pair = staffWheelChairFactory.getStaffWheelChairBasedOnCode(statusCode);
				pair.add(staffQueueAL.remove());
				break;
		case 0: staff = (Staff) staffQueueAL.remove();
				wheelChair = (Wheel_Chair) wheelchairQueueAL.remove();
				staffService.updateStaffStatus(staff.getId());
				wheelChairService.UpdateStatus(wheelChair.getId());
				pair.add(staffQueueAL.remove());
				pair.add(wheelchairQueueAL.remove());
				break;
		}
		return pair;
	}
}
