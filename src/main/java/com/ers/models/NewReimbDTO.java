package com.ers.models;

public class NewReimbDTO {

	public double amount;
	public String description;
	public int userId;
	public String type;
	
	public NewReimbDTO() {
		super();
	}

	public NewReimbDTO(double amount, String description, int userId, String type) {
		super();
		this.amount = amount;
		this.description = description;
		this.userId = userId;
		this.type = type;
	}
}
