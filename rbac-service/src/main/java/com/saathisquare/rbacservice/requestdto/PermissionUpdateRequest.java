package com.saathisquare.rbacservice.requestdto;

import java.util.List;

public record PermissionUpdateRequest(
	    String action, // "UPDATE" or "REMOVE"
	    List<PermissionChange> permissions
	) {}

	