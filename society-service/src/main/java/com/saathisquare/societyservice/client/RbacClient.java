package com.saathisquare.societyservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.saathisquare.societyservice.dto.response.UserDetailsResponse;
import com.saathisquare.societyservice.util.Response;

@FeignClient(name = "rbac-service", url = "${rbac-service.base-url}")
public interface RbacClient {
	
	@GetMapping("/api/users/{username}")
	ResponseEntity<Response<UserDetailsResponse>> getLoginDetailsByUsername(@PathVariable String username);

}
