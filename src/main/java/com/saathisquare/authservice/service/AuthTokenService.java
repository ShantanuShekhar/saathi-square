package com.saathisquare.authservice.service;

import com.saathisquare.authservice.model.AuthToken;

public interface AuthTokenService {

	AuthToken findByToken(String token);

	void create(AuthToken authToken);

}
