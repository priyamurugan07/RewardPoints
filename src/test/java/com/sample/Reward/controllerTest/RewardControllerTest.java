package com.sample.Reward.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.sample.Reward.controller.RewardController;
import com.sample.Reward.dto.RPListDTO;
import com.sample.Reward.service.RewardService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RewardController.class)
public class RewardControllerTest {

	@InjectMocks
	RewardController rewardController;

	@MockitoBean
	RewardService rewardService;

	@Test
	void calculateRewardPointsTest() throws Exception {
	  	  RPListDTO mockData = new RPListDTO();
		  when(rewardService.calculateThreeMonthsRewardPoints(anyInt())).thenReturn(mockData);
		  ResponseEntity<RPListDTO> response =rewardController.calculateThreeMonthsRewardPoints(anyInt());
		  assertNotNull(response);
		  assertEquals(HttpStatus.OK,response.getStatusCode());
	}
	  
	  @Test
	  void calculateAllMonthRewardPointsTest() throws Exception {
		  RPListDTO mockData = new RPListDTO();
		  when(rewardService.calculateAllMonthRewardPoints(anyInt())).thenReturn(mockData);
		  ResponseEntity<RPListDTO> response =rewardController.calculateAllMonthRewardPoints(anyInt());
		  assertNotNull(response);
		  assertEquals(HttpStatus.OK,response.getStatusCode());
	  }
	 
	 
}
