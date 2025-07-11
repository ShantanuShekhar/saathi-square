package com.saathisquare.rbacservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saathisquare.rbacservice.dto.responsedto.UserDetailsRequest;
import com.saathisquare.rbacservice.dto.responsedto.UserDetailsResponse;
import com.saathisquare.rbacservice.model.Feature;
import com.saathisquare.rbacservice.model.ParentFeature;
import com.saathisquare.rbacservice.model.Permission;
import com.saathisquare.rbacservice.model.Role;
import com.saathisquare.rbacservice.model.User;
import com.saathisquare.rbacservice.repository.PermissionRepository;
import com.saathisquare.rbacservice.repository.UserRepository;
import com.saathisquare.rbacservice.requestdto.PermissionChange;
import com.saathisquare.rbacservice.requestdto.PermissionUpdateRequest;
import com.saathisquare.rbacservice.requestdto.RbacSetupRequest;
import com.saathisquare.rbacservice.service.FeatureService;
import com.saathisquare.rbacservice.service.ParentFeatureService;
import com.saathisquare.rbacservice.service.RbacService;
import com.saathisquare.rbacservice.service.RoleService;
import com.saathisquare.rbacservice.util.Constants;
import com.saathisquare.rbacservice.util.Response;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RbacServiceImpl implements RbacService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RbacServiceImpl.class);

	private final UserRepository userRepository;
	private final PermissionRepository permissionRepository;
	private final RoleService roleService;
	private final ParentFeatureService parentFeatureService;
	private final FeatureService featureService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public boolean userHasPermission(String username, String featurePath, String permissionType) {
		// @formatter:off
       userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return permissionRepository.userHasPermission(username, featurePath, permissionType);

//        Role role = user.getRole();
//        if (role == null) return false;
//
//        return role.getPermissions().stream()
//                .anyMatch(p -> p.getFeature().getPath().equalsIgnoreCase(featurePath) &&
//                               p.getPermissionType().equalsIgnoreCase(permissionType));
     // @formatter:on
	}

	@Override
	public List<Feature> getFeaturesForRole(Long roleId) {
		// @formatter:off
        return permissionRepository.findByRoleId(roleId).stream()
                .map(Permission::getFeature)
                .distinct()
                .toList();
     // @formatter:on
	}

	@Override
	public List<Permission> getPermissionsForUser(String username) {
		// @formatter:off
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
     // @formatter:on

		Role role = user.getRole();
		return role != null ? new ArrayList<>(role.getPermissions()) : List.of();
	}

	@Override
	@Transactional
	public void setupRbacData(RbacSetupRequest request) {
		// Step 1: Save Role
		Role savedRole = roleService.create(request.getRole());

		// Step 2: Save Parent Features
		List<ParentFeature> savedParents = new ArrayList<>();
		for (ParentFeature pf : request.getParentFeatures()) {
			ParentFeature savedParent = parentFeatureService.create(pf);
			savedParents.add(savedParent);
		}

		// Step 3: Save Features and link to saved ParentFeature
		List<Feature> savedFeatures = new ArrayList<>();
		for (Feature f : request.getFeatures()) {
			if (f.getParentFeature() != null) {
				ParentFeature matchedParent = savedParents.stream()
						.filter(p -> p.getName().equalsIgnoreCase(f.getParentFeature().getName())).findFirst()
						.orElse(null);
				f.setParentFeature(matchedParent);
			}
			Feature savedFeature = featureService.create(f);
			savedFeatures.add(savedFeature);
		}

		// Step 4: Save Permissions (link Role and Feature)
		for (Permission p : request.getPermissions()) {
			Role role = savedRole; // Always the same savedRole
			Feature matchedFeature = savedFeatures.stream()
					.filter(f -> f.getName().equalsIgnoreCase(p.getFeature().getName())).findFirst().orElse(null);

			p.setRole(role);
			p.setFeature(matchedFeature);
			permissionRepository.save(p);
		}

		// Step 5: Save User with Role
		User user = request.getUser();
		user.setRole(savedRole);
		userRepository.save(user);
	}

	@Transactional
	public void modifyRolePermissions(Long roleId, PermissionUpdateRequest request) {
		Role role = roleService.findById(roleId);

		Set<Permission> permissions = role.getPermissions(); // Don't replace this!

		if (request.action().equalsIgnoreCase("REMOVE")) {
			request.permissions().forEach(change -> {
				permissions.removeIf(p -> p.getFeature().getId().equals(change.featureId()));
			});

		} else if (request.action().equalsIgnoreCase("UPDATE")) {
			for (PermissionChange change : request.permissions()) {
				// Remove old permissions for that feature
				permissions.removeIf(p -> p.getFeature().getId().equals(change.featureId()));

				Feature feature = featureService.findById(change.featureId());

				for (String type : change.permissionTypes()) {
					Permission newPerm = new Permission();
					newPerm.setPermissionType(type);
					newPerm.setFeature(feature);
					newPerm.setRole(role);
					permissions.add(newPerm); // Add directly to original set
				}
			}
		}

		// Save the role (cascade persists permission changes)
		roleService.create(role);
	}

	@Override
	@Transactional
	public Response<UserDetailsResponse> updateUserDetails(UserDetailsRequest request) {
		LOGGER.info("Inside : updateUserDetails : getting request is :{}", request);

		Response<UserDetailsResponse> response = new Response<UserDetailsResponse>();

		User user = userRepository.findById(request.id()).orElseThrow(() -> new NotFoundException("User not found"));

		// Only update non-null fields
		if (StringUtils.isNotBlank(request.firstName())) {
			user.setFirstName(request.firstName());
		}
		if (StringUtils.isNotBlank(request.lastName())) {
			user.setLastName(request.lastName());
		}
		if (StringUtils.isNotBlank(request.newPassword())) {
			user.setPassword(passwordEncoder.encode(request.newPassword()));
		}
		if (request.roleName() != null) {
			Role role = roleService.findByName(request.roleName());
			user.setRole(role);
		}

		user = userRepository.save(user);
		UserDetailsResponse dto = getRespDto(user);
		response.setStatus(Constants.SUCCESS_CODE);
		response.setMessage(Constants.UPDATE_SUCCESS_MESSAGE);
		response.setData(dto);
		return response;

	}

	private UserDetailsResponse getRespDto(User user) {

		String firstName = StringUtils.defaultString(user.getFirstName(), "");
		String lastName = StringUtils.defaultString(user.getLastName(), "");
		String fullName = (firstName + " " + lastName).trim();

		return UserDetailsResponse.builder().id(user.getId()).username(StringUtils.defaultString(user.getEmail())) // assuming
				.email(StringUtils.defaultString(user.getEmail()))
				.fullName(StringUtils.isNotBlank(fullName) ? fullName : null)
				.password(StringUtils.defaultIfBlank(user.getPassword(), null))
				.roleName(user.getRole() != null ? StringUtils.defaultString(user.getRole().getName()) : null).build();
	}

}
