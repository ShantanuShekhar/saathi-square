package com.saathisquare.rbacservice.service;

import com.saathisquare.rbacservice.dto.responsedto.UserDetailsResponse;
import com.saathisquare.rbacservice.requestdto.SignupRequest;
import com.saathisquare.rbacservice.util.Response;

public interface UserService {

	Response<String> create(SignupRequest request);

	Response<UserDetailsResponse> getLoginDetailsbyUserName(String username);

}
