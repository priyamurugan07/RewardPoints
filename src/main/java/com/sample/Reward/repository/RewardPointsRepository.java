package com.sample.Reward.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sample.Reward.entity.RewardPoints;

@Repository
public interface RewardPointsRepository extends JpaRepository<RewardPoints, Integer> {
	
}
