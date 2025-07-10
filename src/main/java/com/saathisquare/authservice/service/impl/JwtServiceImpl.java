package com.saathisquare.authservice.service.impl;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.saathisquare.authservice.service.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration-ms}")
	private long expirationMs;

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expirationMs))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}

	@Override
	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody().getSubject();
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		final Date expiration = Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
				.getBody().getExpiration();
		return expiration.before(new Date());
	}

	@Override
	public long getExpirationMs() {
		return expirationMs;
	}

}
