package com.sample.Reward.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.Reward.Exception.RewardException;
import com.sample.Reward.dto.BaseResponseDTO;
import com.sample.Reward.dto.CustomerDTO;
import com.sample.Reward.dto.LoginRequestDTO;
import com.sample.Reward.dto.LoginResponseDTO;
import com.sample.Reward.service.CustomerService;
import com.sample.Reward.service.TokenService;

import io.swagger.v3.oas.annotations.tags.Tag;

	/**
	 * 
	 * This Controller handles customer login and registration  and logout requests with authenticated tokens.
	 * It provides end points for registering new customer, logging in and logout. 
	 * In case of any errors, it handles exceptions and return response with an error message.
	 *  
	 */

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer Controller API")
public class AuthenticationController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private TokenService tokenService;
	
	/**
	 * For registering new customer
	 * 
	 * @param customerDTO customerDTO the customer details for registration.
	 * @return a success message
	 * @throws RewardException if the given credentials are invalid
	 * 
	 */
	@PostMapping("/register")
	public ResponseEntity<BaseResponseDTO> registerCustomer(@RequestBody CustomerDTO customerDTO)throws RewardException{
		return new ResponseEntity<>(customerService.registerCustomer(customerDTO),HttpStatus.CREATED);
	}

	/**
	 * Login customer by validating their credentials
	 * 
	 * @param loginRequestDTO loginRequestDTO the login details(email and password)
	 * @return a token for authenticated customer
	 * @throws RewardException if the given login request datas are invalid
	 * 
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> loginCustomer(@RequestBody LoginRequestDTO loginRequestDTO)throws RewardException{
		return new ResponseEntity<>(customerService.loginCustomer(loginRequestDTO),HttpStatus.OK);
	}
	
	/**
	 * Log out customer with token details
	 * 
	 * @param jwt a token created for each customer while login.
	 * @return a success message
	 * @throws RewardException if the token is invalid
	 * 
	 */
	@PostMapping("/logout")
	public ResponseEntity<BaseResponseDTO> logoutCustomer(@RequestHeader("Authorization") String jwt)throws RewardException{
		String token = jwt.replace("Bearer ", "");
		return new ResponseEntity<>(tokenService.revokeToken(token),HttpStatus.OK);
		
	}

}
