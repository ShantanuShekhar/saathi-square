package com.saathisquare.societyservice.model;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import com.saathisquare.societyservice.enums.OccupancyStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "flat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flat {

	@Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM) // UUIDv4
    @JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "flat_id",nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
	private UUID flatId;
	private String tower;

	@Column(name = "flat_number")
	private String flatNumber;

	@Column(name = "area_sqft")
	private Double areaSqft;
	@Enumerated(EnumType.STRING)
	@Column(name = "occupancy_status")
	private OccupancyStatus occupancyStatus;

	@Column(name = "unit_rate")
	private Double unitRate;

	@Column(name = "is_deleted")
	private boolean isDeleted; // Soft delete flag

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	private Society society;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "floor_id")
	private Floor floor;

}
