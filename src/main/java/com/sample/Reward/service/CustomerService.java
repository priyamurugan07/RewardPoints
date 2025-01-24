package com.sample.Reward.service;


import org.springframework.security.core.userdetails.UserDetailsService;

import com.sample.Reward.Exception.RewardException;
import com.sample.Reward.dto.BaseResponseDTO;
import com.sample.Reward.dto.CustomerDTO;
import com.sample.Reward.dto.LoginRequestDTO;
import com.sample.Reward.dto.LoginResponseDTO;

public interface CustomerService extends UserDetailsService{
	
	BaseResponseDTO registerCustomer(CustomerDTO customerDTO)throws RewardException;
	
	LoginResponseDTO loginCustomer(LoginRequestDTO loginRequestDTO)throws RewardException;
	

}
