package com.saathisquare.rbacservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysql.cj.log.Log;
import com.saathisquare.rbacservice.dto.responsedto.LoginResponseDto;
import com.saathisquare.rbacservice.dto.responsedto.UserDetailsResponse;
import com.saathisquare.rbacservice.model.Role;
import com.saathisquare.rbacservice.model.User;
import com.saathisquare.rbacservice.model.UserStatus;
import com.saathisquare.rbacservice.repository.UserRepository;
import com.saathisquare.rbacservice.requestdto.SignupRequest;
import com.saathisquare.rbacservice.service.RoleService;
import com.saathisquare.rbacservice.service.UserService;
import com.saathisquare.rbacservice.util.Constants;
import com.saathisquare.rbacservice.util.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;
	private final RoleService roleService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Response<String> create(SignupRequest request) {
		Response<String> response = new Response<>();
		findByEmail(request.email());
		Role role = roleService.findByRoleName(request.role());
		if (role == null)
			role = new Role();
		role.setName(request.role());
		roleService.create(role);
		// Create user entity

		String pwd = passwordEncoder.encode(request.password());
		LOGGER.info("getting encrypted pwd is : {}", pwd);

		User user = User.builder().firstName(request.firstname()).lastName(request.lastname()).email(request.email())
				.userStatus(UserStatus.ACTIVE).password(passwordEncoder.encode(request.password())).role(role).build();
		userRepository.save(user);

		response.setStatus(Constants.SUCCESS_CODE);
		response.setMessage("Registration Completed Successfully : " + user.getFirstName()
				+ " your Registration Id is : " + user.getId());
		return response;

	}

	private void findByEmail(String email) {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new IllegalArgumentException("Email already registered: " + email);
		}

	}

	@Override
	public Response<UserDetailsResponse> getLoginDetailsbyUserName(String email) {
		UserDetailsResponse login = findLoginDetailsByEmail(email);
		LOGGER.info("getting login response is {}", login);
		if (login == null)
			throw new IllegalArgumentException("User Details not found: " + email);
		return new Response<UserDetailsResponse>(Constants.SUCCESS_CODE, Constants.RETRIVED_SUCCESS_MESSAGE, login);
	}

	private UserDetailsResponse findLoginDetailsByEmail(String email) {
		LoginResponseDto dto = userRepository.getLoginDetailsByEmail(email);
		LOGGER.info("getting dto is  {}", dto);
		if (dto == null)
			return null;
		return getLoginDto(dto);
	}

	private UserDetailsResponse getLoginDto(LoginResponseDto dto) {
		if (dto == null) {
			return null;
		}

		return UserDetailsResponse.builder().id(dto.getId()).username(dto.getUserName()).email(dto.getUserName())
				.fullName(dto.getFirstName() + " " + dto.getLastName()).password(dto.getPassword())
				.roleName(dto.getRoleName()).build();

	}

}
