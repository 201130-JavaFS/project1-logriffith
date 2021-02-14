package com.ers.models;

public class StatusDTO {
	
	public int statusId;
	public String statusType;
	
	public StatusDTO() {
		super();
	}

	public StatusDTO(int statusId, String statusType) {
		super();
		this.statusId = statusId;
		this.statusType = statusType;
	}
}
