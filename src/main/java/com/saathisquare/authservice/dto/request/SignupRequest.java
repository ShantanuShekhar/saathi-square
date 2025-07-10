package com.saathisquare.authservice.dto.request;

public record SignupRequest(String firstname, String lastname, String email, String password, String role) {
}
