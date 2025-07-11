package com.saathisquare.societyservice.dto.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class UserDetailsResponse {

	private UUID id;
	private String username;
	private String email;
	private String fullName;
	private String password;
	private String roleName; // Can include "ROLE_ADMIN", "ROLE_RESIDENT", etc.
}
