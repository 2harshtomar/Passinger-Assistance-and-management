package com.wcm.utility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TimerTask;

import com.wcm.model.Staff;
import com.wcm.model.Wheel_Chair;
import com.wcm.repository.StaffRepository;
import com.wcm.repository.WheelChairRepository;
import com.wcm.service.StaffService;
import com.wcm.service.WheelChairService;

public class StationExp extends TimerTask implements StaffWheelChairFactory {
	
	private StaffRepository repository;
	private WheelChairRepository wcRepo;
	private Queue<Object> staffQueueExp;
	private Set<Object> staffSetExp;
	private Queue<Object> wheelchairQueueExp;
	private Set<Object> wheelchairSetExp;
	
	private StaffService staffService = new StaffService();
	private WheelChairService wheelChairService = new WheelChairService();
	
	private String code;
	private String status;
	
	
	
	public StationExp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StationExp(StaffRepository repository, WheelChairRepository wheelChairRepository, String code, String status) {
		super();
		this.wcRepo = wheelChairRepository;
		this.repository = repository;
		this.code = code;
		this.status = status;
		System.out.println("created");
	
	}
	
	public StaffRepository getRepository() {
		return repository;
	}
	public void setRepository(StaffRepository repository) {
		this.repository = repository;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public WheelChairRepository getWcRepo() {
		return wcRepo;
	}
	public void setWcRepo(WheelChairRepository wcRepo) {
		this.wcRepo = wcRepo;
	}
	@Override
	public void createQueue() {
		this.staffQueueExp = new LinkedList<>();
		this.staffSetExp = new HashSet<>();
		this.wheelchairQueueExp = new LinkedList<>();
		this.wheelchairSetExp = new HashSet<>();

//		System.out.println("QUEUE created");
	}
	public void updateStaffQueue() {
		this.staffSetExp = repository.getStaffSet(this.code, this.status);
		this.staffSetExp.removeAll(this.staffQueueExp);
		this.staffQueueExp.addAll(this.staffSetExp);
		this.staffSetExp.clear();
//		System.out.println("hit");
	}
	
	public void updateWheelChairQueue() {
		this.wheelchairSetExp = wcRepo.getWheelChairSet("MAA",true);
		this.wheelchairSetExp.removeAll(this.wheelchairQueueExp);
		this.wheelchairQueueExp.addAll(this.wheelchairSetExp);
		this.wheelchairSetExp.clear();
//		System.out.println("wc_hit");
	}
	@Override
	public void displayQueue() {
		System.out.println("STAFF EXP QUEUE DATA - "+this.staffQueueExp.toString());
		System.out.println("WHEEL CHAIR QUEUE DATA - "+ this.wheelchairQueueExp.toString());
	}
	public void ClearResources() {
		this.staffQueueExp = null;
		this.staffSetExp = null;
		this.wheelchairQueueExp = null;
		this.wheelchairSetExp = null;
		this.staffService = null;
		this.wheelChairService = null;
//		System.out.println("Resources nullified");
	}
	@Override
	public int getQueueStatus() {
		boolean staff = !this.staffQueueExp.isEmpty();
		boolean wc = !this.wheelchairQueueExp.isEmpty();
		if(staff && wc) { return 3;}
		else if(staff == true && wc == false) {return 2;}
		else if(staff == false && wc == true) {return 1;}
		else {return 0;}
	}
	@Override
	public List<Object> getStaffWheelChairBasedOnCode(int code) {
		List<Object> wheelChairStaff = new ArrayList<>();
		Wheel_Chair wheelChair = new Wheel_Chair();
		Staff staff = new Staff();
		switch (code) {
		case 3: staff = (Staff) this.staffQueueExp.remove();
				wheelChair = (Wheel_Chair) this.wheelchairQueueExp.remove();
				this.staffService.updateStaffStatus(staff.getId());
				this.wheelChairService.UpdateStatus(wheelChair.getId());
				wheelChairStaff.add(staff);
				wheelChairStaff.add(wheelChair);
				return wheelChairStaff;
		case 2: staff = (Staff) this.staffQueueExp.remove();
				this.staffService.updateStaffStatus(staff.getId());
				wheelChairStaff.add(this.staffQueueExp.remove());
				return wheelChairStaff;
		case 1: wheelChair = (Wheel_Chair) this.wheelchairQueueExp.remove();
				this.wheelChairService.UpdateStatus(wheelChair.getId());
				wheelChairStaff.add(this.wheelchairQueueExp.remove());
				return wheelChairStaff;
	}
		return wheelChairStaff;
	
}
	@Override
	public void run() {
		updateStaffQueue();
		updateWheelChairQueue();
	}
	@Override
	public Object getStaff() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getWheelChair() {
		// TODO Auto-generated method stub
		return null;
	}

}
