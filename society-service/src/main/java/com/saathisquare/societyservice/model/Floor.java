package com.saathisquare.societyservice.model;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "floor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Floor {
	@Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM) // UUIDv4
    @JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "id",nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
	private UUID floorId;

	@Column(name = "floor_number")
	private Integer floorNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tower_id")
	private Tower tower;
}
