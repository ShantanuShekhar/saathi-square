package com.saathisquare.rbacservice.model;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import com.mysql.cj.protocol.a.NativeConstants.StringLengthDataType;

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
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
@Builder
public class User {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.RANDOM) // UUIDv4
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", nullable = false, updatable = false, columnDefinition = "VARCHAR(36)")
	private UUID id;

	@Column(name = "Email", unique = true, nullable = false, length = 100)
	private String email;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(name = "LastName", nullable = false, length = 50)
	private String lastName;

	@Column(name = "Password", nullable = false, length = 150)
	private String password;

	@Column(name = "UserStatus")
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RoleId", nullable = false)
	private Role role;

	@CreationTimestamp
	@Column(name = "CreatedOn")
	private Date createdOn;

	@UpdateTimestamp
	@Column(name = "UpdatedOn")
	private Date updatedOn;

	@Column(name = "LastLoginTime")
	private Date lastLoginTime;

}