package com.saathisquare.societyservice.dto.response;

import java.util.UUID;

public record FlatResponse(UUID flatId, String tower, String flatNumber, Double areaSqft, String occupancyStatus) {
}
