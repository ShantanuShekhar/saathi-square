package com.saathisquare.authservice.dto.request;

public record UserDetailsRequest(Long id, String username, String email, String firstName, String lastName,
		String newPassword, String oldPassword, String roleName) {
	
	
}
