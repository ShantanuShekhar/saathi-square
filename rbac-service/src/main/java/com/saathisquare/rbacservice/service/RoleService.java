package com.saathisquare.rbacservice.service;

import com.saathisquare.rbacservice.model.Role;

public interface RoleService {

	Role create(Role role);

	Role findById(Long roleId);

	Role findByName(String role);

	Role findByRoleName(String roleName);


}
