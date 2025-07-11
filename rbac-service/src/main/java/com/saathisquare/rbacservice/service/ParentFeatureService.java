package com.saathisquare.rbacservice.service;

import com.saathisquare.rbacservice.model.ParentFeature;

public interface ParentFeatureService {
	ParentFeature create(ParentFeature pf);

	ParentFeature findById(Long parentFeatureId);
}
