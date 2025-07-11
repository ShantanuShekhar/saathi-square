package com.saathisquare.societyservice.service;

import com.saathisquare.societyservice.dto.request.AssignUserRequest;
import com.saathisquare.societyservice.dto.request.CreateFlatRequest;
import com.saathisquare.societyservice.dto.response.FlatResponse;

public interface FlatService {

	FlatResponse createFlat(CreateFlatRequest request);

	String assignUserToFlat(AssignUserRequest request);

}
