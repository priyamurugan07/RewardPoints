package com.sample.Reward.service;

import com.sample.Reward.Exception.RewardException;
import com.sample.Reward.dto.BaseResponseDTO;
import com.sample.Reward.dto.CustomerTransactionDTO;
import com.sample.Reward.dto.TransactionRequestDTO;

public interface CustomerTransactionService {

	BaseResponseDTO createTransaction(TransactionRequestDTO transactionRequestDTO) throws RewardException ;

	CustomerTransactionDTO getTransactionById(int transactionId) throws RewardException;

	BaseResponseDTO updateTransaction(Integer transactionId,TransactionRequestDTO transactionRequestDTO) throws RewardException ;

	BaseResponseDTO deleteTransaction(Integer transactionId) throws RewardException ;
	
	

}
