package com.saathisquare.rbacservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "parent_features")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentFeature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false, unique = true, length = 100)
	private String name; // e.g., "Security", "Billing", "HR"

	@OneToMany(mappedBy = "parentFeature", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Feature> features;
}
