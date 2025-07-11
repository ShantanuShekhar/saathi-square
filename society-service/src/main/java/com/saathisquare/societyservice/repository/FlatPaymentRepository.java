package com.saathisquare.societyservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saathisquare.societyservice.model.FlatPayment;

@Repository
public interface FlatPaymentRepository extends JpaRepository<FlatPayment, UUID> {
}
