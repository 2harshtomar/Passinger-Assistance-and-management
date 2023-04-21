package com.wcm.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcm.ApplicationShutDownManager;
import com.wcm.dto.ResStaffDto;
import com.wcm.dto.ResponseDto;
import com.wcm.exception.ResourseNotFoundException;
import com.wcm.model.Ssr;
import com.wcm.repository.SsrRepository;
import com.wcm.repository.WheelChairRepository;
import com.wcm.service.AirlineService;
import com.wcm.service.SationServiceMAA;
import com.wcm.service.StaffService;
import com.wcm.service.StationRouterService;
import com.wcm.service.StationServiceDL;
import com.wcm.service.WheelChairService;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

//	@Autowired
//	private WheelChairRepository wcRepo;

	@Autowired
	private WheelChairService wcService;

	@Autowired
	private ResponseDto responseDto;

	@Autowired
	private StaffService staffService;
	
	@Autowired
	private AirlineService airlineService;

	@Autowired
	private SationServiceMAA stationServiceMaa;
	
	@Autowired
	private StationServiceDL stationServiceDL;
	
	@Autowired
	private WheelChairRepository wcrepo;
	
	@Autowired
	private StationRouterService stationRouterService;

	@Autowired
	private ApplicationShutDownManager shutDownManager;
	
	@Autowired
	private SsrRepository ssrRepo;

	// changing the status of wheel chair
	@PutMapping("/update/wc/status/{id}")
	public ResponseEntity<Object> UpdateStatus(@PathVariable("id") Long id){
		try {
			wcService.UpdateStatus(id);
			responseDto.setMessage("Status Updated");
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} catch (ResourseNotFoundException e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}
	
	// changing the status of staff
	@PutMapping("/update/staff/status/{id}")
	public ResponseEntity<Object> updateStaffStatus(@PathVariable("id") Long id){
		return staffService.updateStaffStatus(id);
	}
	
	@GetMapping("/get/staff/based/on/code")
	public List<ResStaffDto> getStaffBasedOnCode(Principal principal){
		return staffService.getStaff(principal);
	}
	@GetMapping("/get/wheel/chair")
	public Set<Object> getWC(){
		return wcrepo.getWheelChairSet("MAA", true);
	}
	
	@GetMapping("/invoke")
	public void demo() {
//		stationService.addElement();
//		stationService.updateStaffQueue();
//		Set<Integer> set1 = new HashSet<>();
//		Set<Integer> set2 = new HashSet<>();
//		set1.add(1);
//		set1.add(2);
////		set1.add(3);
//		set2.add(1);
//		set2.add(2);
//		System.out.println(set1);
//		System.out.println(set2);
//		set1.removeAll(set2);
//		System.out.println(set1);
		stationServiceMaa.displayQueue();
		stationServiceDL.displayQueue();
		airlineService.displayQueue();

	}
	@GetMapping("/get/{id}")
	public String getCode(@PathVariable("id") Long id) {
//		StationSpecialService stationSpecialService = new StationSpecialService("MAA");
		return stationRouterService.getEntity(id);
	
	}
	@GetMapping("/get/pair/{code}")
	public List<Object> getPair(@PathVariable("code") String code){
		return airlineService.RequestStation(code);
	}
//	@GetMapping("/send")
//	public ResponseEntity<Object> sendSMS() {
//		String msg = "Task assigned";
//		return staffService.sendSMS(msg);
//	}
	
	@GetMapping("/shutdown")
	public void shutDown() {
		stationServiceMaa.DeleteQueue();
		stationServiceDL.DeleteQueue();
		airlineService.DeleteQueue();
		shutDownManager.initiateShutdown(0);
		System.out.println("Terminated");
	}
	
	@GetMapping("/getssr")
	public List<Ssr> getssr() {
		Long i = (long) 102;
		return ssrRepo.getSsrOnStaff(i);
	}
}
