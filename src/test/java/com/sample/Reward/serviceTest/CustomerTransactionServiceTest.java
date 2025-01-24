package com.sample.Reward.serviceTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.sample.Reward.Exception.RewardException;
import com.sample.Reward.dto.BaseResponseDTO;
import com.sample.Reward.dto.CustomerTransactionDTO;
import com.sample.Reward.dto.TransactionRequestDTO;
import com.sample.Reward.entity.Customer;
import com.sample.Reward.entity.CustomerTransaction;
import com.sample.Reward.repository.CustomerTransactionRepository;
import com.sample.Reward.service.CustomerTransactionSeriveImpl;

@SpringBootTest
@ContextConfiguration(classes = {CustomerTransactionSeriveImpl.class} )
public class CustomerTransactionServiceTest {

	@MockitoBean
    CustomerTransactionRepository customerTransactionRepository;
	
	@InjectMocks
	CustomerTransactionSeriveImpl customerTransactionSeriveImpl;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	 void createTransactionTest() throws RewardException {
		
		CustomerTransaction customerTransaction = new CustomerTransaction();
		
		Customer customer = new Customer();
		customer.setCustomerId(10);
		customerTransaction.setCustomer(customer);
		customerTransaction.setTransactionId(1);
		customerTransaction.setSpentDetails("Bags");
		customerTransaction.setAmount(500);
		customerTransaction.setTransactionDate(LocalDate.of(2025,01,12));
		
		TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
		transactionRequestDTO.setCustomerId(10);
		transactionRequestDTO.setSpentDetails("Bags");
		transactionRequestDTO.setAmount(500);
		
		when(customerTransactionRepository.save(customerTransaction)).thenReturn(customerTransaction);
		BaseResponseDTO response = customerTransactionSeriveImpl.createTransaction(transactionRequestDTO);
		assertNotNull(response);
	}
	
	@Test
	void getTransactionById() throws RewardException{
		
		CustomerTransaction customerTransaction = new CustomerTransaction();
		Customer customer = new Customer();
		customer.setCustomerId(10);
		customerTransaction.setCustomer(customer);
		customerTransaction.setTransactionId(1);
		customerTransaction.setSpentDetails("Bags");
		customerTransaction.setAmount(500);
		customerTransaction.setTransactionDate(LocalDate.of(2025,01,12));
		
		CustomerTransactionDTO mockData = new CustomerTransactionDTO();
		mockData.setCustomerId(10);
		mockData.setTransactionId(1);
		mockData.setSpentDetails("Bags");
		mockData.setAmount(500);
		mockData.setTransactionDate(LocalDate.of(2025,01,12));
		when(customerTransactionRepository.findById(customerTransaction.getTransactionId())).thenReturn(Optional.of(customerTransaction));
		CustomerTransactionDTO response = customerTransactionSeriveImpl.getTransactionById(customerTransaction.getTransactionId());
		assertNotNull(response);
	}
	
	@Test
    void updateTransaction() throws RewardException{
		
		CustomerTransaction customerTransaction = new CustomerTransaction();
		Customer customer = new Customer();
		customer.setCustomerId(10);
		customerTransaction.setCustomer(customer);
		customerTransaction.setTransactionId(1);
		customerTransaction.setSpentDetails("Bags");
		customerTransaction.setAmount(500);
		customerTransaction.setTransactionDate(LocalDate.of(2025,01,12));
		
		when(customerTransactionRepository.findById(customerTransaction.getTransactionId())).thenReturn(Optional.of(customerTransaction));
		when(customerTransactionRepository.save(customerTransaction)).thenReturn(customerTransaction);
		
		TransactionRequestDTO transactionRequestDTO = new TransactionRequestDTO();
		transactionRequestDTO.setCustomerId(10);
		transactionRequestDTO.setSpentDetails("Bags");
		transactionRequestDTO.setAmount(500);
		
		BaseResponseDTO response = customerTransactionSeriveImpl.updateTransaction(customerTransaction.getTransactionId(), transactionRequestDTO);
		assertNotNull(response);
	}
	
	@Test
	void deleteTransaction() throws RewardException{
		
		CustomerTransaction customerTransaction = new CustomerTransaction();
		Customer customer = new Customer();
		customer.setCustomerId(10);
		customerTransaction.setCustomer(customer);
		customerTransaction.setTransactionId(1);
		customerTransaction.setSpentDetails("Bags");
		customerTransaction.setAmount(500);
		customerTransaction.setTransactionDate(LocalDate.of(2025,01,12));
		
		when(customerTransactionRepository.findById(customerTransaction.getTransactionId())).thenReturn(Optional.of(customerTransaction));
		BaseResponseDTO response = customerTransactionSeriveImpl.deleteTransaction(customerTransaction.getTransactionId());
		assertNotNull(response);
	}
	

}
