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
		
		Optional<Customer> optCustomer = customerRepository.findById(transactionRequestDTO.getCustomerId());
		if(optCustomer.isPresent()) {
			CustomerTransaction customerTransaction = new CustomerTransaction();
			customerTransaction.setCustomer(optCustomer.get());
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
		
		Optional<CustomerTransaction> optCustomerTransaction = customerTransactionRepository.findById(transactionId);
		
		CustomerTransactionDTO transactionResponse = new CustomerTransactionDTO();
		if(optCustomerTransaction.isPresent()) {
			
			CustomerTransaction customerTransaction = optCustomerTransaction.get();
			transactionResponse.setTransactionId(customerTransaction.getTransactionId());
			transactionResponse.setCustomerId(customerTransaction.getCustomer().getCustomerId());
			transactionResponse.setSpentDetails(customerTransaction.getSpentDetails());
			transactionResponse.setAmount(customerTransaction.getAmount());
			transactionResponse.setTransactionDate(customerTransaction.getTransactionDate());
		}
		else {
			throw new RewardException("TransactionId not found");
		}
		return transactionResponse;
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
		
		Optional<CustomerTransaction> optCustomerTransaction = customerTransactionRepository.findById(transactionId);

		Optional<Customer> optCustomer = customerRepository.findById(transactionRequestDTO.getCustomerId());
		if(optCustomer.isEmpty()) {
			throw new RewardException("CustomerId not found");
		}
		if(optCustomerTransaction.isPresent()) {
			CustomerTransaction customerTrans = optCustomerTransaction.get();
			customerTrans.setCustomer(optCustomer.get());
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
