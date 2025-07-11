package com.saathisquare.societyservice.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.saathisquare.societyservice.enums.PaymentStatus;

public record PaymentResponse(UUID paymentId, Double amount, LocalDateTime paymentDate, PaymentStatus status) {
}
