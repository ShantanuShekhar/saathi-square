package com.saathisquare.authservice.dto.response;

public record LoginResponse(String token, String username, String roleName) {
}
