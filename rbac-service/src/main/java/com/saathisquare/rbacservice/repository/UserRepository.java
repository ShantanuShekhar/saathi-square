package com.saathisquare.rbacservice.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saathisquare.rbacservice.dto.responsedto.LoginResponseDto;
import com.saathisquare.rbacservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByEmail(String email);

	@Query("""
			    SELECT u FROM User u
			    JOIN FETCH u.role r
			    JOIN FETCH r.permissions p
			    JOIN FETCH p.feature f
			    JOIN FETCH f.parentFeature pf
			    WHERE u.id = :id
			""")
	Optional<User> findFullUserDetailsById(@Param("id") Long id);

	@Query("""
			    SELECT u FROM User u
			    JOIN FETCH u.role r
			    JOIN FETCH r.permissions p
			    JOIN FETCH p.feature f
			    JOIN FETCH f.parentFeature pf
			    WHERE u.email = :email
			""")
	Optional<User> findFullUserDetailsByEmail(@Param("email") String email);

	@Query("""
			    SELECT u.id AS id,
			           u.firstName AS firstName,
			           u.lastName AS lastName,
			           u.email AS userName,
			           u.password AS password,
			           r.name AS roleName
			    FROM User u
			    JOIN u.role r
			    WHERE u.email = :email
			""")
	LoginResponseDto getLoginDetailsByEmail(@Param("email") String email);

}
