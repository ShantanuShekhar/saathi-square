package com.saathisquare.societyservice.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saathisquare.societyservice.dto.request.CreateSocietyRequest;
import com.saathisquare.societyservice.dto.request.SocietyDataRequest;
import com.saathisquare.societyservice.dto.response.PaginatedResponse;
import com.saathisquare.societyservice.dto.response.SocietyResponse;
import com.saathisquare.societyservice.dto.response.UserSocietyDashboardCount;
import com.saathisquare.societyservice.model.Society;
import com.saathisquare.societyservice.service.SocietyService;
import com.saathisquare.societyservice.util.Response;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/societies")
@RequiredArgsConstructor
public class SocietyController {
	private final SocietyService service;

	@PostMapping
	public ResponseEntity<Response<SocietyResponse>> create(@RequestBody CreateSocietyRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createSociety(request));
	}

	@GetMapping("/{id}/{username}")
	public ResponseEntity<Response<SocietyResponse>> getById(@PathVariable UUID id, @PathVariable String username) {
		return ResponseEntity.ok(service.getSocietyDetailsBySocietyId(id, username));
	}

	@GetMapping("/{all}")
	public ResponseEntity<Response<PaginatedResponse<Society>>> getAll(@RequestBody SocietyDataRequest request) {
		return ResponseEntity.ok(service.getAllPaginated(request));
	}

	@GetMapping("/{id}/getCount")
	public ResponseEntity<Response<UserSocietyDashboardCount>> getSocietyMappingCount(@PathVariable String id) {
		return ResponseEntity.ok(service.getSocietyMappingCountByUserId(id));
	}
	
//	@PostMapping("/tower")
//	public ResponseEntity<Response<UserSocietyDashboardCount>> getSocietyMappingCount(@PathVariable String id) {
//		return ResponseEntity.ok(service.getSocietyMappingCountByUserId(id));
//	}
//	
//	@PostMapping("/floor")
//	public ResponseEntity<Response<UserSocietyDashboardCount>> getSocietyMappingCount(@PathVariable String id) {
//		return ResponseEntity.ok(service.getSocietyMappingCountByUserId(id));
//	}
	

}
