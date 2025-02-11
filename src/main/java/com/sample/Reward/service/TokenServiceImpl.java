package com.sample.Reward.service;

import java.time.LocalDateTime;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.Reward.dto.BaseResponseDTO;
import com.sample.Reward.entity.CustomerTokens;
import com.sample.Reward.repository.CustomerRepository;
import com.sample.Reward.repository.TokenRepository;

	 /**
	  * 
	  * Service class for managing JWT tokens in the database.
	  *
  */
@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	public TokenServiceImpl(TokenRepository tokenRepository) {
		super();
		this.tokenRepository = tokenRepository;
	}
	
	/**
	 * Save a new token in the database.
	 * 
	 * @param email email associated with the token.
	 * @param token JWT token string
	 * @param expireDate the expiration time of the token.
	 * 
	 */
	@Override
	public void saveToken(String email, String token, LocalDateTime expireDate) {
			
		Optional<CustomerTokens> optTokens = tokenRepository.findByToken(token);
		
		if(optTokens.isPresent()) {
		  tokenRepository.deleteById(optTokens.get().getTokenId());
		}
		
		CustomerTokens customerTokens = new CustomerTokens();
		customerTokens.setEmail(email);
		customerTokens.setToken(token);
		customerTokens.setCreatedDate(LocalDateTime.now());
		customerTokens.setExpireDate(expireDate);
		
		tokenRepository.save(customerTokens);
	}
	
	/**
	 * Find tokens by its value
	 * 
	 * @param token JWT token string
	 * @return Optional containing matching customer tokens
	 * 
	 */
	@Override
	public Optional<CustomerTokens> findByToken(String token){
		return tokenRepository.findByToken(token);
	}
	
	/**
	 * Revokes a specific token by removing it from the database.
	 * 
	 * @param token JWT token to be revoked
	 * @return success message
	 */
	@Override
	public BaseResponseDTO revokeToken(String token) {
		
		Optional<CustomerTokens> optTokens = tokenRepository.findByToken(token);
		if(optTokens.isPresent()) {
		  tokenRepository.deleteById(optTokens.get().getTokenId());
		}
		return new BaseResponseDTO("Logged Out Successfully");
	}	

}
