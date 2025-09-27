package com.saathisquare.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.saathisquare.authservice.dto.request.SignupRequest;
import com.saathisquare.authservice.dto.request.UserDetailsRequest;
import com.saathisquare.authservice.dto.response.UserDetailsResponse;
import com.saathisquare.authservice.util.Response;

@FeignClient(name = "rbac-service", url = "${rbac-service.base-url}")
public interface RbacClient {

	@PostMapping("/api/create/user")
	ResponseEntity<Response<String>> createUser(@RequestBody SignupRequest request);

	@GetMapping("/api/users/{username}")
	ResponseEntity<Response<UserDetailsResponse>> getLoginDetailsByUsername(@PathVariable String username);

	@GetMapping("/{email}/details")
	ResponseEntity<?> getFullUserDetailsByUsername(@PathVariable String username);

	@PostMapping("/api/users/update")
	ResponseEntity<Response<UserDetailsResponse>> updateUser(@RequestBody UserDetailsRequest request);

}
