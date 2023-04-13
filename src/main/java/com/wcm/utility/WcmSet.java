package com.wcm.utility;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
@Service
public class WcmSet {
	public Set<Object> createSet(){
		Set<Object> set = new HashSet<>();
		return set;
	}
}
