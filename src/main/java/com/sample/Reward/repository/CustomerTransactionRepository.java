package com.sample.Reward.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.Reward.entity.CustomerTransaction;

	/**
	 * Repository Interface for customer_transactions database operations.
	 */
@Repository
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Integer>{
 
	/**
	 * Find Customer Transaction by id, start date and end date.
	 * 
	 * @param customerId Id of the customer
	 * @param startDate start date of the transaction 
	 * @param endDate end date of the transaction
	 * @return a list of customer transactions
	 * 
	 */
	List<CustomerTransaction> findByCustomerCustomerIdAndTransactionDateBetween(int customerId, LocalDate startDate,LocalDate endDate);
	
	/**
	 * Find Customer Transaction by customerId.
	 * 
	 * @param customerId Id of the customer
	 * @return a list of customer transactions
	 * 
	 */
	List<CustomerTransaction> findAllByCustomerCustomerId(int customerId);
}
