package com.saathisquare.authservice.service;

import com.saathisquare.authservice.dto.request.LoginRequest;
import com.saathisquare.authservice.dto.request.SignupRequest;
import com.saathisquare.authservice.dto.request.UserDetailsRequest;
import com.saathisquare.authservice.dto.response.LoginResponse;
import com.saathisquare.authservice.dto.response.UserDetailsResponse;
import com.saathisquare.authservice.util.Response;

public interface AuthService {

	LoginResponse login(LoginRequest request);

	Response<String> signup(SignupRequest request);

	void logout(String token);

	Response<UserDetailsResponse> updateUserDetails(UserDetailsRequest request);
}
