package com.saathisquare.societyservice.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import com.saathisquare.societyservice.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Payment made by a flat user for a specific plan
@Entity
@Table(name = "flat_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlatPayment {
	@Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM) // UUIDv4
    @JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "payment_id",nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
	private UUID paymentId;

	@ManyToOne(fetch = FetchType.LAZY)
	private Flat flat;

	@ManyToOne(fetch = FetchType.LAZY)
	private PaymentPlan paymentPlan;

	private Double amount;
	private LocalDateTime paymentDate;
	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status")
	private PaymentStatus paymentStatus; // PAID / UNPAID
}
