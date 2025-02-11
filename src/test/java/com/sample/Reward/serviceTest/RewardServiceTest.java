package com.sample.Reward.serviceTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.sample.Reward.Exception.RewardException;
import com.sample.Reward.dto.RPListDTO;
import com.sample.Reward.entity.Customer;
import com.sample.Reward.entity.CustomerTransaction;
import com.sample.Reward.repository.CustomerTransactionRepository;
import com.sample.Reward.service.RewardServiceImpl;


@SpringBootTest
@ContextConfiguration(classes = {RewardServiceImpl.class} )
public class RewardServiceTest {

	@MockitoBean
        CustomerTransactionRepository customerTransactionRepository;
	
	@InjectMocks
	RewardServiceImpl rewardServiceImpl;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void calculateThreeMonthsRewardPoints() throws RewardException{
		
		List<CustomerTransaction> mockDataList = new ArrayList<>();
		
		CustomerTransaction mockData = new CustomerTransaction();
		Customer customer = new Customer();
		customer.setCustomerId(10);
		mockData.setCustomer(customer);
		mockData.setTransactionId(1);
		mockData.setSpentDetails("Bags");
		mockData.setAmount(500);
		mockData.setTransactionDate(LocalDate.of(2024,12,12));
		
		mockDataList.add(mockData);
		
		when(customerTransactionRepository.findByCustomerCustomerIdAndTransactionDateBetween(mockData.getCustomer().getCustomerId(),LocalDate.of(2025,01,12),LocalDate.of(2024,10,12))).thenReturn(mockDataList);
		RPListDTO response = rewardServiceImpl.calculateThreeMonthsRewardPoints(mockDataList.get(0).getCustomer().getCustomerId());
		assertNotNull(response);
	}
	@Test
	void calculateAllMonthRewardPoints() throws RewardException{
		
		List<CustomerTransaction> mockDataList = new ArrayList<>();
		
		CustomerTransaction mockData = new CustomerTransaction();
		Customer customer = new Customer();
		customer.setCustomerId(10);
		mockData.setCustomer(customer);
		mockData.setTransactionId(1);
		mockData.setSpentDetails("Bags");
		mockData.setAmount(500);
		mockData.setTransactionDate(LocalDate.of(2025,01,12));
		
		mockDataList.add(mockData);
		
		when(customerTransactionRepository.findAllByCustomerCustomerId(mockData.getCustomer().getCustomerId())).thenReturn(mockDataList);
		RPListDTO response = rewardServiceImpl.calculateAllMonthRewardPoints(mockDataList.get(0).getCustomer().getCustomerId());
		assertNotNull(response);
	}
}
