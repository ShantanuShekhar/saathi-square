package com.saathisquare.rbacservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saathisquare.rbacservice.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
	List<Permission> findByRoleId(Long roleId);

	@Query("""
			    SELECT COUNT(p) > 0 FROM Permission p
			    JOIN p.feature f
			    WHERE p.role = (
			        SELECT u.role FROM User u WHERE u.email = :username
			    )
			    AND f.path = :featurePath
			    AND p.permissionType = :permissionType
			""")
	boolean userHasPermission(@Param("username") String username, @Param("featurePath") String featurePath,
			@Param("permissionType") String permissionType);

}
