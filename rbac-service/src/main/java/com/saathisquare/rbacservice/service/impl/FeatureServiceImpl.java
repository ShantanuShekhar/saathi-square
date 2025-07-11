package com.saathisquare.rbacservice.service.impl;

import org.springframework.stereotype.Service;

import com.saathisquare.rbacservice.model.Feature;
import com.saathisquare.rbacservice.repository.FeatureRepository;
import com.saathisquare.rbacservice.service.FeatureService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

	private final FeatureRepository featureRepository;

	@Override
	public Feature create(Feature f) {
		return featureRepository.save(f);
	}

	@Override
	public Feature findById(Long featureId) {
		return featureRepository.findById(featureId).orElseThrow(() -> new RuntimeException("Feature not found"));
	}

}
