package com.saathisquare.societyservice.dto.response;

import java.util.UUID;

public record SocietyResponse(UUID societyId, String name, String location, String billingCycle, UUID createdBy) {
}
