package com.ers.models;

public class NewReimbDTO {

	public double amount;
	public String description;
	public String type;
	
	public NewReimbDTO() {
		super();
	}

	public NewReimbDTO(double amount, String description, String type) {
		super();
		this.amount = amount;
		this.description = description;
		this.type = type;
	}
}
