package com.saathisquare.authservice.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saathisquare.authservice.client.RbacClient;
import com.saathisquare.authservice.dto.response.UserDetailsResponse;
import com.saathisquare.authservice.model.AuthUser;
import com.saathisquare.authservice.model.CustomUserDetails;
import com.saathisquare.authservice.util.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final RbacClient rbacClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResponseEntity<Response<UserDetailsResponse>> response = rbacClient.getLoginDetailsByUsername(username);
		UserDetailsResponse data = response.getBody().getData();
		if (data == null) {
			throw new UsernameNotFoundException("User not found: " + username);
		}
		AuthUser authUser = new AuthUser(data.getUsername(), data.getPassword(), data.getRoleName());
		return new CustomUserDetails(authUser);
	}
}
