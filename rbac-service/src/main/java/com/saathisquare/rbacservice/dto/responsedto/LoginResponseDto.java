package com.saathisquare.rbacservice.dto.responsedto;

import java.util.UUID;

public interface LoginResponseDto {

	String getFirstName();

	String getLastName();

	String getPassword();

	String getRoleName();
	
	String getUserName();
	
	UUID getId();
}
