package com.saathisquare.rbacservice.service.impl;

import org.springframework.stereotype.Service;

import com.saathisquare.rbacservice.model.ParentFeature;
import com.saathisquare.rbacservice.repository.ParentFeatureRepository;
import com.saathisquare.rbacservice.service.ParentFeatureService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParentFeatureServiceImpl implements ParentFeatureService {

	private final ParentFeatureRepository parentFeatureRepository;

	@Override
	public ParentFeature create(ParentFeature pf) {
		return parentFeatureRepository.save(pf);
	}

	@Override
	public ParentFeature findById(Long parentFeatureId) {
		return parentFeatureRepository.findById(parentFeatureId)
			        .orElseThrow(() -> new RuntimeException("ParentFeature not found"));
	}

}
