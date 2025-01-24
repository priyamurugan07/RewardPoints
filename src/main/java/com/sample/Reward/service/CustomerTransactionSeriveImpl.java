package com.sample.Reward.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.Reward.Exception.RewardException;
import com.sample.Reward.dto.BaseResponseDTO;
import com.sample.Reward.dto.CustomerTransactionDTO;
import com.sample.Reward.dto.TransactionRequestDTO;
import com.sample.Reward.entity.Customer;
import com.sample.Reward.entity.CustomerTransaction;
import com.sample.Reward.repository.CustomerRepository;
import com.sample.Reward.repository.CustomerTransactionRepository;

/**
 * 
 * Service class for adding, retrieving, updating and deleting transactions.
 *
 */
@Service
public class CustomerTransactionSeriveImpl implements CustomerTransactionService{

	@Autowired
	private CustomerTransactionRepository customerTransactionRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	/**
	 * Adds a new transaction for a customer.
	 * 
	 * @param transactionRequestDTO transaction details of the customer.
	 * @return a success message
	 * @throws RewardException if the customerId does not exist.
	 * 
	 */
	@Override
	public BaseResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO) throws RewardException{
		
		Optional<Customer> optCust=customerRepository.findById(transactionRequestDTO.getCustomerId());
		if(optCust.isPresent()) {
		CustomerTransaction customerTransaction = new CustomerTransaction();
		customerTransaction.setCustomer(optCust.get());
		customerTransaction.setSpentDetails(transactionRequestDTO.getSpentDetails());
		customerTransaction.setAmount(transactionRequestDTO.getAmount());
		customerTransaction.setTransactionDate(LocalDate.now());
		
		customerTransactionRepository.save(customerTransaction);
		}
		else {
			throw new RewardException("CustomerId not found");
		}
		return new BaseResponseDTO("Successfully Created");
	}
	
	/**
	 * Retrieves the transaction based on customerID.
	 * 
	 * @param transactionId ID of the transaction
	 * @return  transaction details of the specific customer.
	 * @throws RewardException if the transaction id does not exist.
	 * 
	 */
	@Override
	public CustomerTransactionDTO getTransactionById(int transactionId) throws RewardException {
		
		Optional<CustomerTransaction> optCustomerTrans = customerTransactionRepository.findById(transactionId);
		
		CustomerTransactionDTO response = new CustomerTransactionDTO();
		if(optCustomerTrans.isPresent()) {
			CustomerTransaction customerTrans = optCustomerTrans.get();
			response.setTransactionId(customerTrans.getTransactionId());
			response.setCustomerId(customerTrans.getCustomer().getCustomerId());
			response.setSpentDetails(customerTrans.getSpentDetails());
			response.setAmount(customerTrans.getAmount());
			response.setTransactionDate(customerTrans.getTransactionDate());
		}
		else {
			throw new RewardException("TransactionId not found");
		}
		return response;
	}
	
	/**
	 * Updates an existing transaction by ID.
	 * 
	 * @param transactionId ID of the transaction to update
	 * @param transactionRequestDTO updated transaction details
	 * @return a success message
	 * @throws RewardException if the transaction id does not exist.
	 * 
	 */
	@Override
	public BaseResponseDTO updateTransaction(Integer transactionId,TransactionRequestDTO transactionRequestDTO) throws RewardException {
		
		Optional<CustomerTransaction> optCustomerTrans = customerTransactionRepository.findById(transactionId);

		Optional<Customer> optCust=customerRepository.findById(transactionRequestDTO.getCustomerId());
		if(optCust.isEmpty()) {
			throw new RewardException("CustomerId not found");
		}
		if(optCustomerTrans.isPresent()) {
			CustomerTransaction customerTrans = optCustomerTrans.get();
			customerTrans.setCustomer(optCust.get());
			customerTrans.setSpentDetails(transactionRequestDTO.getSpentDetails());
			customerTrans.setAmount(transactionRequestDTO.getAmount());
			customerTrans.setTransactionDate(LocalDate.now());
		
		customerTransactionRepository.save(customerTrans);
		}
		else {
			throw new RewardException("TransactionId not found");
		}
		return new BaseResponseDTO("Successfully Updated");
	}

	/**
	 * Deletes the transaction by ID.
	 * 
	 * @param transactionId ID of the transaction to delete.
	 * @return a success message.
	 * @throws RewardException if the transaction id does not exist.
	 */
	@Override
	public BaseResponseDTO deleteTransaction(Integer transactionId) throws RewardException  {
		
		Optional<CustomerTransaction> optCustomerTrans = customerTransactionRepository.findById(transactionId);
		
		if(optCustomerTrans.isPresent()) {
		 customerTransactionRepository.deleteById(transactionId);
		}
		else {
			throw new RewardException("TransactionId not found");
		}
		return new BaseResponseDTO("Successfully Deleted");

	}

}
