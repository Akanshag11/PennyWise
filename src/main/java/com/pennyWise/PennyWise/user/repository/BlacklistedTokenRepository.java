package com.pennyWise.PennyWise.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository extends JpaRepository {
    boolean existsByJti(String jti);
}
