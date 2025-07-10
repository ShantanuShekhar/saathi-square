package com.saathisquare.authservice.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

	String generateToken(UserDetails userDetails);

	long getExpirationMs();

	boolean isTokenValid(String token, UserDetails userDetails);

	String extractUsername(String token);

}
