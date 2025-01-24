package com.sample.Reward.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.Reward.entity.CustomerTokens;
	/**
	 * Repository Interface for performing CRUD operations of CustomerTokens entity.
	 */
@Repository
public interface TokenRepository extends JpaRepository<CustomerTokens, Long>{
	
	/**
	 * Find tokens by its value
	 * 
	 * @param token JWT token string
	 * @return Optional containing matching customer tokens
	 * 
	 */
	Optional<CustomerTokens> findByToken(String token);
	
	/**
	 * Deletes a token by its value
	 * 
	 * @param token token JWT token string
	 * 
	 */
	void deleteByToken(String token);
	
	/**
	 * Deletes all tokens associated with the email.
	 * 
	 * @param email  email whose token should be deleted.
	 * 
	 */
	void deleteByEmail(String email);
	
}
