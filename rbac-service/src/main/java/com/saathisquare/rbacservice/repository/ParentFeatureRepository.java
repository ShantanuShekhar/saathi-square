package com.saathisquare.rbacservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.saathisquare.rbacservice.model.ParentFeature;

@Repository
public interface ParentFeatureRepository extends JpaRepository<ParentFeature, Long> {
	Optional<ParentFeature> findByName(String name);
}
