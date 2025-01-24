package com.sample.Reward.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.sample.Reward.dto.BaseResponseDTO;
import com.sample.Reward.entity.CustomerTokens;

public interface TokenService {


	void saveToken(String email, String token, LocalDateTime expireDate);
	
	Optional<CustomerTokens> findByToken(String token);

	BaseResponseDTO revokeToken(String token);

	void revokeAllTokenForCustomer(String email);
}
