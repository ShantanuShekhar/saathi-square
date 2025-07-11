package com.saathisquare.societyservice.model;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import com.saathisquare.societyservice.enums.SocietyUserMappingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "society_user_mapping")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocietyUserMapping {

	@Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM) // UUIDv4
    @JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "id",nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
	private UUID id;

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "user_id", columnDefinition = "VARCHAR(36)")
	private UUID userId; // from RBAC service

	@ManyToOne
	@JoinColumn(name = "society_id", nullable = false)
	private Society society;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private SocietyUserMappingStatus status; // ACTIVE, DEACTIVE

	@Column(name = "joined_at")
	private Date joinedAt;
}
