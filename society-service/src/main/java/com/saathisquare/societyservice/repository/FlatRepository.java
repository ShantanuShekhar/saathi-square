package com.saathisquare.societyservice.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saathisquare.societyservice.model.Flat;

public interface FlatRepository extends JpaRepository<Flat, UUID> {
	List<Flat> findBySocietySocietyIdAndIsDeletedFalse(UUID societyId);
}
