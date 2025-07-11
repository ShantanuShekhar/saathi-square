package com.saathisquare.rbacservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saathisquare.rbacservice.model.Feature;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
	Optional<Feature> findByPath(String path);

}
