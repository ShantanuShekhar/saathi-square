package com.saathisquare.authservice.dto.request;

public record PasswordUpdateRequest(String oldPassword, String newPassword) {
}
