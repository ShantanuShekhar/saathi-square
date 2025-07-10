package com.saathisquare.authservice.service.impl;

import org.springframework.stereotype.Service;

import com.saathisquare.authservice.model.AuthToken;
import com.saathisquare.authservice.repositry.AuthTokenRepository;
import com.saathisquare.authservice.service.AuthTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthTokenServiceImpl implements AuthTokenService {
	private final AuthTokenRepository authTokenRepository;

	@Override
	public AuthToken findByToken(String token) {
		return authTokenRepository.findByToken(token)
				.orElseThrow(() -> new IllegalArgumentException("Token not found"));
	}

	@Override
	public void create(AuthToken authToken) {
		authTokenRepository.save(authToken);
	}
}
