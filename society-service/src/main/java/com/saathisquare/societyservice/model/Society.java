package com.saathisquare.societyservice.model;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "society")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Society {
	@Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM) // UUIDv4
    @JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "society_id",nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
	private UUID societyId;
	private String name;
	private String location;
	@Column(name = "billing_cycle") // e.g. Monthly, Quarterly etc. to support payment cycles
	private String billingCycle;
	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "created_by",columnDefinition = "VARCHAR(36)")
	private UUID createdBy;
	@CreationTimestamp
	@Column(name = "created_at")
	private Date createdAt;
}
