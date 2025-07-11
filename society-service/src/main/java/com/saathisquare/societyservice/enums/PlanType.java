package com.saathisquare.societyservice.enums;

public enum PlanType {

	MAINTENANCE("Maintenance");

	private final String displayName;

	PlanType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

}
