package com.saathisquare.authservice.model;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "auth_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "user_id",columnDefinition = "VARCHAR(36)")
	private UUID userId;

	@Column(name = "token", nullable = false, unique = true, columnDefinition = "TEXT")
	private String token;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private TokenStatus status;

	@Column(name = "issued_at", nullable = false)
	private Instant issuedAt;

	@Column(name = "expires_at", nullable = false)
	private Instant expiresAt;

	@Column(name = "revoked_at")
	private Instant revokedAt;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "user_agent", columnDefinition = "TEXT")
	private String userAgent;

	public enum TokenStatus {
		ACTIVE, REVOKED, EXPIRED
	}
}
