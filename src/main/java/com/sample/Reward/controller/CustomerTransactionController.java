package com.sample.Reward.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.Reward.Exception.RewardException;
import com.sample.Reward.dto.BaseResponseDTO;
import com.sample.Reward.dto.CustomerTransactionDTO;
import com.sample.Reward.dto.TransactionRequestDTO;
import com.sample.Reward.service.CustomerTransactionService;

import io.swagger.v3.oas.annotations.tags.Tag;

	/**
	 * 
	 * Controller for managing customer transactions.
	 * REST endpoints for adding,viewing,updating and deleting a transaction.
	 * In case of any errors, it handles exceptions and return response with an error message.
	 * 
	 */
@RestController
@RequestMapping("/customer")
@Tag(name ="Customer Transaction Controller CRUD API")
public class CustomerTransactionController {
	
	@Autowired
	private CustomerTransactionService customerTransactionService;
	
	/**
	 * Adds a new transaction for a customer
	 * 
	 * @param transactionRequestDTO transaction details oof the customer
	 * @return  a success message
	 * @throws RewardException if the request contains invalid data.
	 * 
	 */
	@PostMapping("/add/transaction")
	public ResponseEntity<BaseResponseDTO> createTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO)throws RewardException{
		return new ResponseEntity<>(customerTransactionService.createTransaction(transactionRequestDTO),HttpStatus.CREATED);
		
	}
	
	/**
	 * Retrieves the transaction by ID
	 * 
	 * @param transactionId ID of the transaction to retrive transaction details
	 * @return CustomerTransaction details
	 * @throws RewardException if the transaction does not exist.
	 * 
	 */
	@GetMapping("/get/transaction")
	public ResponseEntity<CustomerTransactionDTO> getTransactionById(@RequestParam Integer transactionId) throws RewardException{
		return new ResponseEntity<>(customerTransactionService.getTransactionById(transactionId),HttpStatus.OK);
	}
	
	/**
	 * Updates an existing transaction
	 * 
	 * @param transactionId ID of the transaction to update
	 * @param transactionRequestDTO the updated transaction details
	 * @return a success message
	 * @throws RewardException if the transaction does not exist.
	 * 
	 */
	@PutMapping("/update/transaction")
	public ResponseEntity<BaseResponseDTO> updateTransaction(@RequestParam Integer transactionId,@RequestBody TransactionRequestDTO transactionRequestDTO)throws RewardException{
		return new ResponseEntity<>(customerTransactionService.updateTransaction(transactionId,transactionRequestDTO),HttpStatus.OK);
		
	}
	
	/**
	 * Deletes a transaction by ID
	 * 
	 * @param transactionId ID of the transaction to delete
	 * @return a success message
	 * @throws RewardException if the transaction does not exist.
	 * 
	 */
	@DeleteMapping("/delete/transaction")
	public ResponseEntity<BaseResponseDTO> deleteTransaction(@RequestParam Integer transactionId)throws RewardException{
		return new ResponseEntity<>(customerTransactionService.deleteTransaction(transactionId),HttpStatus.OK);
		
	}

}
