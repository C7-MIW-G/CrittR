package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.CrittrUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CrittrUserRepository extends JpaRepository<CrittrUser, Long> {
    Optional<CrittrUser> findByEmail(String email);
}
