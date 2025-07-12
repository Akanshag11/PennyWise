package com.pennyWise.PennyWise.user.repository;

import com.pennyWise.PennyWise.user.model.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken,String> {
    boolean existsByJti(String jti);
}

