package com.saathisquare.getway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {
	private static final String SECRET = "your-256-bit-secret-your-256-bit-secret";

	public static Claims validateToken(String token) {
		Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
	}

}
