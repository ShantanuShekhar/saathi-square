package com.saathisquare.societyservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.saathisquare.societyservice.dto.response.UserSocietyDashboardCount;
import com.saathisquare.societyservice.model.SocietyUserMapping;

@Repository
public interface SocietyUserMappingRepository extends JpaRepository<SocietyUserMapping, UUID> {

	@Query(value = """
			SELECT
			    sum_stats.user_id AS userId,
			    COUNT(DISTINCT sum_stats.society_id) AS societyCount,
			    COUNT(DISTINCT t.id) AS towerCount,
			    COUNT(DISTINCT f.id) AS floorCount,
			    COUNT(DISTINCT fl.flat_id) AS flatCount
			FROM (
			    SELECT user_id, society_id
			    FROM society_user_mapping
			    WHERE user_id = :userId
			) AS sum_stats
			LEFT JOIN tower t ON t.society_id = sum_stats.society_id
			LEFT JOIN floor f ON f.tower_id = t.id
			LEFT JOIN flat fl ON fl.floor_id = f.id
			GROUP BY sum_stats.user_id
			""", nativeQuery = true)
		UserSocietyDashboardCount getAllCountsForUser(@Param("userId") String userId);
}
