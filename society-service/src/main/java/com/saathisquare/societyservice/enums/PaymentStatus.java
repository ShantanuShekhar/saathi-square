package com.saathisquare.societyservice.enums;

public enum PaymentStatus {

	PAID("Paid"), UNPAID("Unpaid");

	private final String displayName;

	PaymentStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
