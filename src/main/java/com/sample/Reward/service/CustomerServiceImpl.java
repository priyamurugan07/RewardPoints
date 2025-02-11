package com.sample.Reward.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sample.Reward.Exception.RewardException;
import com.sample.Reward.dto.BaseResponseDTO;
import com.sample.Reward.dto.CustomerDTO;
import com.sample.Reward.dto.LoginRequestDTO;
import com.sample.Reward.dto.LoginResponseDTO;
import com.sample.Reward.entity.Customer;
import com.sample.Reward.repository.CustomerRepository;
import com.sample.Reward.security.JwtUtil;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	@Lazy
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	/**
	 * Registers a new customer by saving the details in database.
	 * In case of any errors, it handles exceptions and return response with an error message.
	 * 
	 * @param CustomerDTO customer details for registration
	 * @throws RewardException if the email is already present.
	 */
	@Override
	public BaseResponseDTO registerCustomer(CustomerDTO customerDTO) throws RewardException{
		
		//Find the customer by email from the database
		Optional<Customer> optCustomer = customerRepository.findByEmail(customerDTO.getEmail());
		
		if(optCustomer.isPresent()) {
			throw new RewardException("Email Already Exists");
		}
		
		//create new customer and hash the password and then save in database
		Customer customer = new Customer();
		customer.setCustomerName(customerDTO.getCustomerName());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhone(customerDTO.getPhone());
		customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
		customer.setIsActive("Y");
		
		customerRepository.save(customer);
		return new BaseResponseDTO("Registered Succesffully!");
	}

	
	/**
	 * Login Customer by validating their credentials.
	 * In case of any errors, it handles exceptions and return response with an error message.
	 * 
	 * @param LoginRequestDTO login details(email and password)
	 * @return a token for authenticated customer
	 * @throws RewardException if the credentials are invalid. 
	 */
	@Override
	public LoginResponseDTO loginCustomer(LoginRequestDTO loginRequestDTO)throws RewardException {
		
		Optional<Customer> customer = customerRepository.findByEmail(loginRequestDTO.getEmail());
		
		if(customer.isEmpty()) {
			throw new RewardException("Email Not Exists");
		}
		String pwd = loginRequestDTO.getPassword();
		String encodedPwd = customer.get().getPassword();
		Boolean isPwdRight = passwordEncoder.matches(pwd, encodedPwd);
		if(isPwdRight) {
			try{
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(),loginRequestDTO.getPassword()));
			}
			catch(Exception e){
				System.out.println("Exception :" + e);
			}
			String token =jwtUtil.generateToken(loginRequestDTO.getEmail());
			
			LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
			loginResponseDTO.setJwt(token);
			return loginResponseDTO;
		}
		else
			throw new RewardException ("Invalid Credentials");
		
	}

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Customer> customer = customerRepository.findByEmail(email);
		return new org.springframework.security.core.userdetails.User(customer.get().getEmail(),customer.get().getPassword(),null);
	}

	}

	
