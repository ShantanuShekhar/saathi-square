package com.saathisquare.rbacservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "features")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feature {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, length = 100)
	private String name; // e.g., "View_Security", "Manage_Security"

	@Column(name = "path", nullable = false, unique = true)
	private String path; // e.g., "Security/View_Security/View_Table"

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_feature_id", nullable = false)
	private ParentFeature parentFeature;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Feature parentFeatureNode;

	@OneToMany(mappedBy = "parentFeatureNode", cascade = CascadeType.ALL)
	private Set<Feature> subFeatures;
	
}
