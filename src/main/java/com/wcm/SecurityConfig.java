package com.wcm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.wcm.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((authorise) -> {
			try {
				// USER API'S
				authorise.requestMatchers("/api/user/login").permitAll()
						 .requestMatchers("/api/user/get/details").permitAll()
						 // STAFF API'S
						 .requestMatchers("/api/staff/add/{userId}").permitAll()
						 .requestMatchers("/api/staff/get/{id}").permitAll()
						 .requestMatchers("/api/staff/get/all").permitAll()
						 // FLIGHT API'S
						 .requestMatchers("/api/flight/details/add/{sid}/{did}/{aid}").permitAll()
						 .requestMatchers("/api/flight/details/get/{id}").permitAll()
						 .requestMatchers("/api/flight/details/get/all").permitAll()
						 .requestMatchers("/api/flight/details/update/flight/{fid}").permitAll()
						 // PASSENGER API'S
						 .requestMatchers("/api/passenger/add/{id}").permitAll()
						 .requestMatchers("/api/passenger/get/{id}").permitAll()
						 .requestMatchers("/api/passenger/get/all").denyAll()
						 // SSR API'S
						 .requestMatchers("/api/ssr/add/{passengerId}").permitAll()
						 .requestMatchers("/api/ssr/staff/get/ssrInfo").authenticated()
						 .requestMatchers("/api/ssr/staff/get/{id}").authenticated()
						 .requestMatchers("/api/ssr/passenger/get/{id}").permitAll()
						 .requestMatchers("/api/ssr/staff/updateArciveStatus/id/{id}").permitAll()
						 .requestMatchers("/api/ssr/update/source/staff/id/{id}").permitAll()
						 .requestMatchers("/api/ssr/staff/get/ssrInfo/id/{staffId}").hasAuthority("STAFF")
						 .requestMatchers("/api/ssr/staff/updateArciveStatus/principal").hasAuthority("STAFF")
						 .requestMatchers("/api/ssr/update/source/staff/principal").hasAuthority("STAFF")
						 .requestMatchers("/api/ssr/update/staff/updateArciveStatus/{ssid}").authenticated()
						 .requestMatchers("/api/ssr/update/source/staff/{ssid}").authenticated()
						 // WHEEL CHAIR API'S
						 .requestMatchers("/api/wheelChair/add/{userID}").permitAll()
						 .requestMatchers("/api/wheelChair/get/all").permitAll()
						 .requestMatchers("/api/wheelChair/get/wheelChair").denyAll()
				.anyRequest()
				 .permitAll()
				 .and()
				 .csrf().disable();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		})
		.httpBasic(Customizer.withDefaults());

		http.authenticationProvider(getDBAuth());
		return http.build();

	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider getDBAuth() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getPasswordEncoder());
		dao.setUserDetailsService(myUserDetailsService);
		return dao;
	}
}
