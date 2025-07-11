package com.saathisquare.rbacservice.requestdto;

public record SignupRequest(String firstname, String lastname, String email, String password, String role) {
}
