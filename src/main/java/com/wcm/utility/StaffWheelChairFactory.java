package com.wcm.utility;

import java.util.List;

public interface StaffWheelChairFactory {
	public void createQueue();
	public Object getStaff();
	public void displayQueue();
	public int getQueueStatus();
	public void DeleteQueue();
	public Object getWheelChair();
	public List<Object> getStaffWheelChairBasedOnCode(int code);
}
