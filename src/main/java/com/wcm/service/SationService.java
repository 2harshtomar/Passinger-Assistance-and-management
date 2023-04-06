package com.wcm.service;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.wcm.utility.WcmQueue;
@Service
public class SationService {
	
	private Queue<Object> staffQueue;
	
	@Autowired
	private WcmQueue wcmQueue;
	
	@EventListener(ApplicationReadyEvent.class)
	public void createQueue() {
		System.out.println("worked");
		this.staffQueue = wcmQueue.createQueue();
		System.out.println("Queue created");
	}
//	@EventListener(ApplicationReadyEvent.class)
	public void addElement() { 
		Set<Integer> demoSet = new HashSet<>();
		demoSet.add(12);
		demoSet.add(23);
		demoSet.add(34);
		demoSet.add(45);
		this.staffQueue.addAll(demoSet);
	}
//	@EventListener(ApplicationReadyEvent.class)
	public void displayQueue() {
		System.out.println(this.staffQueue);
	}
	public void DeleteQueue() {
		staffQueue = null;
		System.out.println("Queue nullified");
	}
}
