package com.sample.Reward.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.Reward.Exception.RewardException;
import com.sample.Reward.dto.RPListDTO;
import com.sample.Reward.service.RewardService;

import io.swagger.v3.oas.annotations.tags.Tag;

	/**
	 * 
	 * Controller for calculating reward points for last 3 month period and all-month for the customer based on transactions.
	 * REST endpoints for getting 3 month points and all-month points.
	 * In case of any errors, it handles exceptions and return response with an error message.
	 * 
	 */
@RestController
@RequestMapping("/rewards")
@Tag(name ="Reward Controller Get API")
public class RewardController {
	
	@Autowired
	private RewardService rewardService;
	
	/**
	 * Calculates reward points for the last 3 month for a customer.
	 * 
	 * @param customerId ID of the customer.
	 * @return  3 month reward points  and the total reward points.
	 * @throws RewardException if the customer does not exist.
	 * 
	 */
	@GetMapping("/points")
	public ResponseEntity<RPListDTO> calculateThreeMonthsRewardPoints(@RequestParam Integer customerId)throws RewardException{
		return new ResponseEntity<>(rewardService.calculateThreeMonthsRewardPoints(customerId), HttpStatus.OK);
	}
	
	/**
	 * Calculates all-time reward points for a customer.
	 * 
	 * @param customerId ID of the customer.
	 * @return all month reward points and the total reward points.
	 * @throws RewardException if the customer does not exist.
	 * 
	 */
	@GetMapping("/all-points")
	public ResponseEntity<RPListDTO> calculateAllMonthRewardPoints(@RequestParam Integer customerId) throws RewardException{
		return new ResponseEntity<>(rewardService.calculateAllMonthRewardPoints(customerId), HttpStatus.OK);
	}
	

}
