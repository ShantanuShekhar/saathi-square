package com.saathisquare.societyservice.enums;

public enum SocietyUserMappingStatus {
	ACTIVE("Active"), DEACTIVE("Deactive");

	private final String displayName;

	SocietyUserMappingStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
