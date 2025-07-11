package com.saathisquare.rbacservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Role linked to this permission
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	// Feature linked to this permission
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feature_id", nullable = false)
	private Feature feature;

	// Type of permission: e.g., READ, WRITE, DELETE, EXECUTE
	@Column(name = "permission_type", nullable = false, length = 20)
	private String permissionType;
}
