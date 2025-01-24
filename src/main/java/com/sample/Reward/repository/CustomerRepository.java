package com.sample.Reward.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.Reward.entity.Customer;

	/**
	 * Repository Interface for customer database operations.
	 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	/**
	 * Find customer by their Email.
	 * 
	 * @param email Email of the customer
	 * @return an optional customer entity
	 */
	Optional<Customer> findByEmail(String email);	
}
