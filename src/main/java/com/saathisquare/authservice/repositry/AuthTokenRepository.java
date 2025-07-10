package com.saathisquare.authservice.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saathisquare.authservice.model.AuthToken;

@Repository
public interface AuthTokenRepository  extends JpaRepository<AuthToken, Long>{

	Optional<AuthToken> findByToken(String token);

}
