package com.wcm.utility;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.springframework.stereotype.Service;

//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.event.EventListener;
@Service
public class WcmQueue {
	// create Queue using defined object
//	@EventListener(ApplicationReadyEvent.class)
	public Queue<Object> createQueue(){
		Queue<Object> queue = new LinkedList<>();
		return queue;
	}

	// Queue operations
	// Add element into queue
	public boolean add(Queue<Object> queue, Set<Object> set) {
		return queue.addAll(set);
	}

	// Retrieving the first element and also removing it
	public Object getElement(Queue<Object> queue) {
		return queue.remove();
	}

	// get queue status, check for empty or not
	public boolean isEmpty(Queue<Object> queue) {
		return queue.isEmpty();
	}
}
