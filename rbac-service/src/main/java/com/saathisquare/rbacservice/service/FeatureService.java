package com.saathisquare.rbacservice.service;

import com.saathisquare.rbacservice.model.Feature;

public interface FeatureService {
	Feature create(Feature f);

	Feature findById(Long featureId);
}
