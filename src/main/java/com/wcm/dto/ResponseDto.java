package com.wcm.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseDto {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
