package com.saathisquare.rbacservice.service;

import java.util.List;

import com.saathisquare.rbacservice.dto.responsedto.UserDetailsRequest;
import com.saathisquare.rbacservice.dto.responsedto.UserDetailsResponse;
import com.saathisquare.rbacservice.model.Feature;
import com.saathisquare.rbacservice.model.Permission;
import com.saathisquare.rbacservice.requestdto.PermissionUpdateRequest;
import com.saathisquare.rbacservice.requestdto.RbacSetupRequest;
import com.saathisquare.rbacservice.util.Response;

public interface RbacService {

	boolean userHasPermission(String username, String featurePath, String permissionType);

	List<Feature> getFeaturesForRole(Long roleId);

	List<Permission> getPermissionsForUser(String username);

	void setupRbacData(RbacSetupRequest request);

	void modifyRolePermissions(Long roleId, PermissionUpdateRequest request);

	Response<UserDetailsResponse> updateUserDetails(UserDetailsRequest request);

}
