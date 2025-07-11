package com.saathisquare.rbacservice.requestdto;

import java.util.List;

public record PermissionChange(
	    Long featureId,
	    List<String> permissionTypes // null or empty for removal
	) {}
