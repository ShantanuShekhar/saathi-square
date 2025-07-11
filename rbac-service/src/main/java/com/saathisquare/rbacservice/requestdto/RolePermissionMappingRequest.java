package com.saathisquare.rbacservice.requestdto;

import java.util.List;

public record RolePermissionMappingRequest(List<FeaturePermissionRequest> permissions) {
}
