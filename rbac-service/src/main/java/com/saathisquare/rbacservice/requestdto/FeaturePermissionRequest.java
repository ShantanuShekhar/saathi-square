package com.saathisquare.rbacservice.requestdto;

import java.util.List;

public record FeaturePermissionRequest(Long featureId, List<String> permissionTypes) {
}
