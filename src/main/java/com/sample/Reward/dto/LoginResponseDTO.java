package com.sample.Reward.dto;

public class LoginResponseDTO {

	private String jwt;

	
	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public LoginResponseDTO(String jwt) {
		this.jwt = jwt;
	}

	public LoginResponseDTO() {
		super();
	}
	
	
	
	
}
