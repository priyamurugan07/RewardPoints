package com.sample.Reward.dto;

import java.util.Map;

public class RPListDTO {
	
	private String status;
	private Map<String, Integer> rewardPoints;
	private int totalPoints;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Map<String, Integer> getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Map<String, Integer> rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	
	public int getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}
	


}
