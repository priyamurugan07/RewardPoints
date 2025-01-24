package com.sample.Reward.dto;

public class BaseResponseDTO {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BaseResponseDTO(String message) {
		super();
		this.message = message;
	}

	public BaseResponseDTO() {
		super();
	}
	
	

}
