package com.sample.Reward.dto;

public class TransactionRequestDTO {
	
	private int customerId ;
	private String spentDetails;
	private double amount;
	
	

	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getSpentDetails() {
		return spentDetails;
	}
	public void setSpentDetails(String spentDetails) {
		this.spentDetails = spentDetails;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
