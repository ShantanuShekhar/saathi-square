package com.saathisquare.societyservice.enums;

public enum OccupancyStatus {
	VACANT("Vacant"), OCCUPIED("Occupied");

	private final String displayName;

	OccupancyStatus(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
