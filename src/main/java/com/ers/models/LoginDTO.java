package com.ers.models;

public class LoginDTO {

	public String username;//DTOs are traditionally public and not encapsulated
	public String password;
	//DTO - Data Transfer Objects are used to transfer data and compare data from the front end to the back end
	//don't use DTOs outside of the controller layer (part of front end)
	

	public LoginDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public LoginDTO() {
		super();
	}
	
}
