package com.sample.Reward.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import com.sample.Reward.controller.CustomerTransactionController;
import com.sample.Reward.dto.BaseResponseDTO;
import com.sample.Reward.dto.CustomerTransactionDTO;
import com.sample.Reward.service.CustomerTransactionService;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerTransactionController.class)
public class CustomerTransactionControllerTest {
	
	@InjectMocks
	CustomerTransactionController customerTransactionController;

	@MockitoBean
	CustomerTransactionService customerTransactionService;
	
	  @Test
	  void createTransactionTest() throws Exception {
	  
	  when(customerTransactionService.createTransaction(any())).thenReturn(new BaseResponseDTO());
	  ResponseEntity<BaseResponseDTO> response =customerTransactionController.createTransaction(any());
	  assertNotNull(response);
	  assertEquals(HttpStatus.CREATED,response.getStatusCode());
	  }
	  
	  @Test
	  void getTransactionByIdTest() throws Exception {
	  
	  CustomerTransactionDTO mockData = new CustomerTransactionDTO();
	  mockData.setCustomerId(1);
	  mockData.setSpentDetails("Bag");
	  mockData.setAmount(200);
	  mockData.setTransactionDate(LocalDate.of(2025,01,12));
	  
	  when(customerTransactionService.getTransactionById(anyInt())).thenReturn(mockData);
	  
	  ResponseEntity<CustomerTransactionDTO> response =customerTransactionController.getTransactionById(anyInt());
	  assertNotNull(response);
	  assertEquals(HttpStatus.OK,response.getStatusCode());
	  }
	  
	  @Test
	  void updateTransactionTest() throws Exception {
	  
	  when(customerTransactionService.updateTransaction(anyInt(),any())).thenReturn(new BaseResponseDTO());
	  ResponseEntity<BaseResponseDTO> response =customerTransactionController.updateTransaction(anyInt(),any());
	  assertNotNull(response);
	  assertEquals(HttpStatus.OK,response.getStatusCode());
	  }
	  
	  @Test
	  void deleteTransactionTest() throws Exception {
	  
	  when(customerTransactionService.deleteTransaction(anyInt())).thenReturn(new BaseResponseDTO());
	  ResponseEntity<BaseResponseDTO> response =customerTransactionController.deleteTransaction(anyInt());
	  assertNotNull(response);
	  assertEquals(HttpStatus.OK,response.getStatusCode());
	  }

}
