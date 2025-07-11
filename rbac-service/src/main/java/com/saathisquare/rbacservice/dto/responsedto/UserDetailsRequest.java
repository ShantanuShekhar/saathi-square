package com.saathisquare.rbacservice.dto.responsedto;

import java.util.UUID;

public record UserDetailsRequest(UUID id, String username, String email, String firstName, String lastName,
		String newPassword, String oldPassword, String roleName) {
}
