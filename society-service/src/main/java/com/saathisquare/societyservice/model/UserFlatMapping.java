package com.saathisquare.societyservice.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

//User-to-flat mapping with history tracking
@Entity
@Table(name = "user_flat_mapping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFlatMapping {
	@Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM) // UUIDv4
    @JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "id",nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
	private UUID id;

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "user_id", columnDefinition = "VARCHAR(36)")
	private UUID userId;

	@ManyToOne(fetch = FetchType.LAZY)
	private Flat flat;

	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private boolean isActive;
}