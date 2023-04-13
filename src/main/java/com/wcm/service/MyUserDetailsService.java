package com.wcm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wcm.model.User;
import com.wcm.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid user credentials");
		}
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRole());
		List<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(sga);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), list);
	}


}
