package com.saathisquare.societyservice.dto.request;

import java.util.UUID;

//Request to assign user to a flat
public record AssignUserRequest(UUID userId, UUID flatId) {
}
