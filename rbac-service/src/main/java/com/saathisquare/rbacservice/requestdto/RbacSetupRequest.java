package com.saathisquare.rbacservice.requestdto;

import java.util.List;

import com.saathisquare.rbacservice.model.Feature;
import com.saathisquare.rbacservice.model.ParentFeature;
import com.saathisquare.rbacservice.model.Permission;
import com.saathisquare.rbacservice.model.Role;
import com.saathisquare.rbacservice.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RbacSetupRequest {
	private User user;
	private Role role;
	private List<ParentFeature> parentFeatures;
	private List<Feature> features;
	private List<Permission> permissions;
}
