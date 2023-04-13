package com.wcm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WcmAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WcmAppApplication.class, args);
		System.out.println("Working");
	}

}

