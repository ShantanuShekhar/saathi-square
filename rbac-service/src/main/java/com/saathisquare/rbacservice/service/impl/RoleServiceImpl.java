package com.saathisquare.rbacservice.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.saathisquare.rbacservice.model.Role;
import com.saathisquare.rbacservice.repository.RoleRepository;
import com.saathisquare.rbacservice.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepository;

	public @Override Role create(Role role) {
		return roleRepository.save(role);

	}

	@Override
	public Role findById(Long roleId) {
		return roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
	}

	@Override
	public Role findByName(String roleName) {
		return roleRepository.findByName(roleName)
				.orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
	}

	@Override
	public Role findByRoleName(String roleName) {
		Optional<Role> role = roleRepository.findByName(roleName);
		if (role.isPresent())
			return role.get();
		return null;
	}
}
