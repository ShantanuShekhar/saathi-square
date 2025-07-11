package com.saathisquare.societyservice.dto.request;

import java.util.UUID;

public record CreateFlatRequest(String tower, String flatNumber, Double areaSqft, UUID societyId) {
}
