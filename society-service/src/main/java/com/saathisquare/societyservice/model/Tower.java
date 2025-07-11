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
@Table(name = "tower")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tower {
	@Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM) // UUIDv4
    @JdbcTypeCode(SqlTypes.VARCHAR)
	@Column(name = "id",nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
    private UUID towerId;
    
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "society_id")
    private Society society;
}
