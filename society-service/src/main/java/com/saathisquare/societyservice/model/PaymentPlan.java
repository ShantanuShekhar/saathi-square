package com.saathisquare.societyservice.model;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import com.saathisquare.societyservice.enums.PlanType;

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

//Payment plan for a society (area-based or fixed)
@Entity
@Table(name = "payment_plan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentPlan {

	@Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM) // UUIDv4
    @JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "id",nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
	private UUID planId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "plan_type", length = 100)
	private PlanType planType; // e.g. Maintenance

	@Column(name = "fixed_amount")
	private Double fixedAmount;
	
	@Column(name = "rate_per_sqft")
	private Double ratePerSqft;

	@Column(name = "description")
	private String description;
	@ManyToOne(fetch = FetchType.LAZY)
	private Society society;
}
