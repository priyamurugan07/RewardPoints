package com.sample.Reward.service;

import com.sample.Reward.Exception.RewardException;
import com.sample.Reward.dto.RPListDTO;

public interface RewardService {
	
	RPListDTO calculateThreeMonthsRewardPoints(int CustomerId) throws RewardException;

	RPListDTO calculateAllMonthRewardPoints(Integer customerId)throws RewardException;
}
