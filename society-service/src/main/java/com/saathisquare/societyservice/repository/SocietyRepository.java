package com.saathisquare.societyservice.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saathisquare.societyservice.model.Society;

@Repository
public interface SocietyRepository extends JpaRepository<Society, UUID> {

	Page<Society> findAllByCreatedBy(UUID fromString, Pageable pageable);
}
