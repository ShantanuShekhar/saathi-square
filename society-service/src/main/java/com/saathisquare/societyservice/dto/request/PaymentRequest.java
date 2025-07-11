package com.saathisquare.societyservice.dto.request;

import java.util.UUID;

public record PaymentRequest(UUID flatId, UUID paymentPlanId) {
}
