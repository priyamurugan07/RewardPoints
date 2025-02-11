package com.sample.Reward.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sample.Reward.Exception.RewardException;
import com.sample.Reward.dto.RPListDTO;
import com.sample.Reward.entity.CustomerTransaction;
import com.sample.Reward.repository.CustomerTransactionRepository;

	/**
	 * 
	 * Service class for calculating customer reward points based on transactions.
	 *
	 */
@Service(value = "rewardService")
public class RewardServiceImpl implements RewardService{
	
	@Value("${maxrewardamount}")
	private int maxRewardAmount;
	
	@Value("${minrewardamount}")
	private int minRewardAmount;
	
	@Value("${rewardpoint}")
	private int rewardPoint;
	
	@Autowired
	private CustomerTransactionRepository customerTransactionRepository;
	
	/**
	 * 
	 * @param CustomerId ID of the customer
	 * @return 3 month reward points  and the total reward points.
	 * @throws RewardException if the customer does not exist.
	 *
	 */
	@Override
	public RPListDTO calculateThreeMonthsRewardPoints(int customerId) throws RewardException {
		
		LocalDate startDate = LocalDate.now().minusMonths(3);
		LocalDate endDate = LocalDate.now();
        
       	 	List<CustomerTransaction> customerTransaction = customerTransactionRepository.findByCustomerCustomerIdAndTransactionDateBetween(customerId, startDate, endDate);
		return calculateRewardPoint(customerTransaction);
	}
	
	/**
	 * 
	 * @param CustomerId ID of the customer
	 * @return all month reward points  and the total reward points.
	 * @throws RewardException if the customer does not exist.
	 *
	 */
	@Override
	public RPListDTO calculateAllMonthRewardPoints(Integer customerId) throws RewardException {
		
		List<CustomerTransaction> customerTransaction = customerTransactionRepository.findAllByCustomerCustomerId(customerId);
		return calculateRewardPoint(customerTransaction);
		
	}
	/**
	 * This method is for calculating reward points based on the list of transaction made by the customer.
	 * 
	 * @param customerTransaction list of customer transactions.
	 * @return the reward points and total points.
	 * @throws RewardException if there is transaction found.
	 * 
	 */
	private RPListDTO calculateRewardPoint(List<CustomerTransaction> customerTransaction) throws RewardException {

		if(customerTransaction.isEmpty()) {
			throw new RewardException("No Data Found");
		}
		Map<String,Integer> map = new HashMap<>();
		int totalpoints = 0;
		
		for(CustomerTransaction transaction : customerTransaction) {
			int points = 0;
			String month = transaction.getTransactionDate().getMonth().toString();
			String year= String.valueOf(transaction.getTransactionDate().getYear()) ;
			double amount = transaction.getAmount();
			
			if(amount>maxRewardAmount) {
				points += (amount-maxRewardAmount) * rewardPoint;
				amount = maxRewardAmount;
			}
			if(amount>minRewardAmount) {
				points += (amount-minRewardAmount);
			}
			
			map.put(month +" " + year, map.getOrDefault(month + " " + year, 0)+points);
			totalpoints += points;
		}
		RPListDTO responseDto = new RPListDTO();
		responseDto.setStatus("success");
		responseDto.setRewardPoints(map);
		responseDto.setTotalPoints(totalpoints);
		
		return responseDto;
	}
}
