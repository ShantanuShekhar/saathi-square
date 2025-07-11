package com.saathisquare.societyservice.dto.request;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlatRequest {
	private String flatNumber;
	private Double areaSqft;
	private String occupancyStatus;
	private Double unitRate;
	private String towerName; // optional
	private UUID societyId;
}
