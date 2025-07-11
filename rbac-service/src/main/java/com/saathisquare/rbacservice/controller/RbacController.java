package com.saathisquare.rbacservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saathisquare.rbacservice.dto.responsedto.UserDetailsRequest;
import com.saathisquare.rbacservice.dto.responsedto.UserDetailsResponse;
import com.saathisquare.rbacservice.model.Feature;
import com.saathisquare.rbacservice.model.ParentFeature;
import com.saathisquare.rbacservice.model.Permission;
import com.saathisquare.rbacservice.model.Role;
import com.saathisquare.rbacservice.model.User;
import com.saathisquare.rbacservice.repository.UserRepository;
import com.saathisquare.rbacservice.requestdto.FeaturePermissionRequest;
import com.saathisquare.rbacservice.requestdto.FeatureRequest;
import com.saathisquare.rbacservice.requestdto.ParentFeatureRequest;
import com.saathisquare.rbacservice.requestdto.PermissionUpdateRequest;
import com.saathisquare.rbacservice.requestdto.RbacSetupRequest;
import com.saathisquare.rbacservice.requestdto.RolePermissionMappingRequest;
import com.saathisquare.rbacservice.requestdto.SignupRequest;
import com.saathisquare.rbacservice.service.FeatureService;
import com.saathisquare.rbacservice.service.ParentFeatureService;
import com.saathisquare.rbacservice.service.RbacService;
import com.saathisquare.rbacservice.service.RoleService;
import com.saathisquare.rbacservice.service.UserService;
import com.saathisquare.rbacservice.util.Response;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RbacController {

	private final RbacService rbacService;
	private final UserRepository userRepository;
	private final ParentFeatureService parentFeatureService;
	private final FeatureService featureService;
	private final RoleService roleService;
	private final UserService userService;

	@PostMapping("/create/user")
	public ResponseEntity<Response<String>> createUser(@RequestBody SignupRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
	}
	
	@GetMapping("/users/{username}")
	ResponseEntity<Response<UserDetailsResponse>> getLoginDetailsByUsername(@PathVariable String username){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.getLoginDetailsbyUserName(username));
	}
	
	@PostMapping("/users/update")
	ResponseEntity<Response<UserDetailsResponse>> updateUser(@RequestBody UserDetailsRequest request){
		return ResponseEntity.ok(rbacService.updateUserDetails(request));
	}
	
	@GetMapping("/permissions/{username}")
	public ResponseEntity<List<Permission>> getPermissions(@PathVariable String username) {
		return ResponseEntity.ok(rbacService.getPermissionsForUser(username));
	}

	@PostMapping("/parent-features")
	public ResponseEntity<?> addParentFeature(@RequestBody ParentFeatureRequest request) {
		ParentFeature parent = new ParentFeature();
		parent.setName(request.name());
		ParentFeature saved = parentFeatureService.create(parent);
		return ResponseEntity.ok(Map.of("id", saved.getId(), "name", saved.getName()));
	}

	@PostMapping("/features")
	public ResponseEntity<?> addFeature(@RequestBody FeatureRequest request) {
		ParentFeature parent = parentFeatureService.findById(request.parentFeatureId());

		Feature feature = new Feature();
		feature.setName(request.name());
		feature.setPath(request.path());
		feature.setParentFeature(parent);

		Feature saved = featureService.create(feature);
		return ResponseEntity.ok(Map.of("id", saved.getId(), "name", saved.getName(), "path", saved.getPath(),
				"parentFeature", parent.getName()));
	}

	@PostMapping("/roles/{roleId}/permissions")
	@Transactional
	public ResponseEntity<?> mapPermissionsToRole(@PathVariable Long roleId,
			@RequestBody RolePermissionMappingRequest request) {
		Role role = roleService.findById(roleId);

		for (FeaturePermissionRequest perm : request.permissions()) {
			Feature feature = featureService.findById(perm.featureId());

			for (String type : perm.permissionTypes()) {
				Permission permission = new Permission();
				permission.setFeature(feature);
				permission.setPermissionType(type);
				permission.setRole(role);
				role.getPermissions().add(permission);
			}
		}
		roleService.create(role);
		return ResponseEntity.ok(Map.of("message", "Permissions mapped successfully"));
	}

	@GetMapping("/has-permission")
	public ResponseEntity<Boolean> checkPermission(@RequestParam String username, @RequestParam String featurePath,
			@RequestParam String permissionType) {
		boolean result = rbacService.userHasPermission(username, featurePath, permissionType);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/features/{roleId}")
	public ResponseEntity<List<Feature>> getFeatures(@PathVariable Long roleId) {
		return ResponseEntity.ok(rbacService.getFeaturesForRole(roleId));
	}


	@PostMapping("/setup")
	public ResponseEntity<String> setupRBAC(@RequestBody RbacSetupRequest request) {
		rbacService.setupRbacData(request);
		return ResponseEntity.ok("RBAC setup completed successfully.");
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<?> getUserDetails(@PathVariable Long id) {
		User user = userRepository.findFullUserDetailsById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));

		Role role = user.getRole();

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("id", user.getId());
		response.put("email", user.getEmail());
		response.put("firstName", user.getFirstName());
		response.put("lastName", user.getLastName());
		response.put("role", role.getName());

		// Group permissions by feature
		Map<Feature, List<Permission>> grouped = role.getPermissions().stream()
				.collect(Collectors.groupingBy(Permission::getFeature));

		List<Map<String, Object>> permissionList = new ArrayList<>();

		for (Map.Entry<Feature, List<Permission>> entry : grouped.entrySet()) {
			Feature feature = entry.getKey();
			List<Permission> permissions = entry.getValue();

			Map<String, Object> permissionMap = new HashMap<>();
			permissionMap.put("parentFeature", feature.getParentFeature().getName());
			permissionMap.put("featureName", feature.getName());
			permissionMap.put("featurePath", feature.getPath());

			List<String> permissionTypes = permissions.stream().map(Permission::getPermissionType).distinct().toList();

			permissionMap.put("permissionTypes", permissionTypes);
			permissionList.add(permissionMap);
		}

		response.put("permissions", permissionList);
		return ResponseEntity.ok(response);
	}
	
	
	
	@GetMapping("/{email}/details")
	public ResponseEntity<?> getAllUserDetailsByEmail(@PathVariable String email) {
		User user = userRepository.findFullUserDetailsByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));

		Role role = user.getRole();

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("id", user.getId());
		response.put("email", user.getEmail());
		response.put("firstName", user.getFirstName());
		response.put("lastName", user.getLastName());
		response.put("role", role.getName());

		// Group permissions by feature
		Map<Feature, List<Permission>> grouped = role.getPermissions().stream()
				.collect(Collectors.groupingBy(Permission::getFeature));

		List<Map<String, Object>> permissionList = new ArrayList<>();

		for (Map.Entry<Feature, List<Permission>> entry : grouped.entrySet()) {
			Feature feature = entry.getKey();
			List<Permission> permissions = entry.getValue();

			Map<String, Object> permissionMap = new HashMap<>();
			permissionMap.put("parentFeature", feature.getParentFeature().getName());
			permissionMap.put("featureName", feature.getName());
			permissionMap.put("featurePath", feature.getPath());

			List<String> permissionTypes = permissions.stream().map(Permission::getPermissionType).distinct().toList();

			permissionMap.put("permissionTypes", permissionTypes);
			permissionList.add(permissionMap);
		}

		response.put("permissions", permissionList);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/roles/{roleId}/permissions")
	public ResponseEntity<?> updatePermissions(@PathVariable Long roleId,
			@RequestBody PermissionUpdateRequest request) {

		rbacService.modifyRolePermissions(roleId, request);
		return ResponseEntity.ok(Map.of("message", "Permissions updated successfully"));
	}

}
