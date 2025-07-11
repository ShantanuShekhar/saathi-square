package com.saathisquare.societyservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saathisquare.societyservice.model.UserFlatMapping;

public interface UserFlatMappingRepository extends JpaRepository<UserFlatMapping, UUID> {
	Optional<UserFlatMapping> findByFlatFlatIdAndIsActiveTrue(UUID flatId);
}