package com.saathisquare.societyservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saathisquare.societyservice.model.Tower;

public interface TowerRepository extends JpaRepository<Tower, UUID> {
	
	boolean existsByNameAndSociety_SocietyId(String towerName, UUID societyId);
}
