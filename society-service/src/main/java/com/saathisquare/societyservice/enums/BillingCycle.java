package com.saathisquare.societyservice.enums;

public enum BillingCycle {

	MONTHELY("Monthely"), QUARTERLY("Quarterly");

	private final String displayName;

	BillingCycle(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
