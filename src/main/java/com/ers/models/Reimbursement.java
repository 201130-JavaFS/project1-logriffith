package com.ers.models;

import java.util.Date;

public class Reimbursement {
	
	private int reimbId;
	private double amount;
	private String submitted;
	private String resolved;
	private String description;
	private int userId;
	private int statusId;
	private int typeId;
	private String type;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbId, double amount, String submitted, String resolved, String description, int userId,
			int statusId, int typeId, String type) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.userId = userId;
		this.statusId = statusId;
		this.typeId = typeId;
		this.type = type;
	}

	public Reimbursement(double amount, String submitted, String resolved, String description, int userId, int statusId,
			int typeId, String type) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.userId = userId;
		this.statusId = statusId;
		this.typeId = typeId;
		this.type = type;
	}

	public Reimbursement(double amount, String submitted, String resolved, String description, int userId, int statusId,
			int typeId) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.userId = userId;
		this.statusId = statusId;
		this.typeId = typeId;
	}
	
	public Reimbursement(double amount, String description, int userId, String type) {
		super();
		this.amount = amount;
		this.description = description;
		this.userId = userId;
		this.type = type;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + statusId;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + typeId;
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (statusId != other.statusId)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (typeId != other.typeId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", amount=" + amount + ", submitted=" + submitted + ", resolved="
				+ resolved + ", description=" + description + ", userId=" + userId + ", statusId=" + statusId
				+ ", typeId=" + typeId + ", type=" + type + "]";
	}

	

	
}
