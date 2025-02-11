package com.sample.Reward.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sample.Reward.entity.Customer;
import com.sample.Reward.entity.CustomerTokens;
import com.sample.Reward.repository.CustomerRepository;
import com.sample.Reward.repository.TokenRepository;
import com.sample.Reward.service.TokenService;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
	
	/**
	 * 
	 * Utility class for  generating and validating JWT tokens.
	 *
	 */
@Component
public class JwtUtil {
	
	@Autowired
	private TokenService tokenService;
	
	//Secret key
	private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	//Expiration date 
	private final int jwtExpirationMs = 86400000;
	
	private CustomerRepository customerRepository;
	
	public JwtUtil(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	/**
	 * Generates a new JWT token and saves it to database
	 * 
	 * @param email email for which the token is generated
	 * @return generated JWT token
	 * 
	 */
	public String generateToken(String email) {
		Customer customer = customerRepository.findByEmail(email).get();
		
		Date expirationDate = new Date(new Date().getTime()+jwtExpirationMs);
		String token = Jwts.builder().setSubject(email).claim(email, customer)
				.setIssuedAt(new Date())
				.setExpiration(expirationDate)
				.signWith(secretKey).compact();
		
		tokenService.saveToken(email, token, LocalDateTime.ofInstant(expirationDate.toInstant(), ZoneId.systemDefault()));
		return token;
	}
	
	//To Extract Email
	public String extractEmail(String token) {
		String extractEmail = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
		return extractEmail;
	}

	//For Token Validation
	public boolean isTokenValid(String token) {
		
		try {
			Optional<CustomerTokens> customerToken = tokenService.findByToken(token);
			Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
			return true;
		}
		catch(JwtException | IllegalArgumentException e){
			return false;
		}
	}
	
	
}
