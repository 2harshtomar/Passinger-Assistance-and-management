package com.wcm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import com.wcm.repository.StaffRepository;
import com.wcm.repository.WheelChairRepository;
import com.wcm.utility.StaffWheelChairFactory;
import com.wcm.utility.StationExp;

public class StationCommonService implements StaffWheelChairFactory {
	private String code;
	private String status;
	private StaffRepository staffRepo;
	private Long period;
	private Long intitialDelay;
	private StationExp exp;
	private Timer stime;
	private WheelChairRepository wheelChairRepository;
	
	public static HashMap<String, StationCommonService> stationDict = new HashMap<>();
	
	
	public StationCommonService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StationCommonService(String code, String status,WheelChairRepository wheelChairRepository, StaffRepository staffRepo, String period,
			String intitialDelay) {
		super();
		this.code = code;
		this.status = status;
		this.staffRepo = staffRepo;
		this.wheelChairRepository = wheelChairRepository;
		this.period = Long.valueOf(period);
		this.intitialDelay = Long.valueOf(intitialDelay);
	}
	
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public StationExp StartStationService(){
		StationExp station = new StationExp(this.staffRepo, this.wheelChairRepository, this.code, this.status);
		Timer time = new Timer();
		this.stime = time;
		station.createQueue();
		stime.schedule(station, this.intitialDelay,this.period );
		this.exp = station;
		return station;
	}
	
	public void deleteQueue() {
		this.exp.ClearResources();
	}
	public void displayQueue() {
		this.exp.displayQueue();
	}
	public void destroyTimer() {
		this.stime.cancel();
	}
	public int getQueueStatus() {
		return this.exp.getQueueStatus();
	}
	public List<Object> getStaffWheelChairBasedOnCode(int code){
		return this.exp.getStaffWheelChairBasedOnCode(code);
	}
	
	public static void setStationOIbject(StationCommonService obj) {
//		int sepPos = obj.getCode().lastIndexOf("-"); //
//	    String stcode = obj.getCode().substring(0,sepPos);
		stationDict.put(obj.getCode(), obj);
	}
	
	public static void clearAllResources() {
		for(Map.Entry<String, StationCommonService> map: stationDict.entrySet()) {
			map.getValue().deleteQueue();
			map.getValue().destroyTimer();
			map.setValue(null);
		}
		stationDict.clear();
	}

	@Override
	public void createQueue() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getStaff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ClearResources() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getWheelChair() {
		// TODO Auto-generated method stub
		return null;
	}
}	
