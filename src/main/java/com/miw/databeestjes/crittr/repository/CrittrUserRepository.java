package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.CrittrUser;
import com.miw.databeestjes.crittr.model.UserRoleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CrittrUserRepository extends JpaRepository<CrittrUser, Long> {
    Optional<CrittrUser> findByEmail(String email);

    @Query("SELECT c FROM CrittrUser c WHERE c.email LIKE ?1")
    List<CrittrUser> listByEmail(String email);

    void deleteByUserId(long userId);

    @Query("SELECT c FROM CrittrUser c WHERE c.email LIKE %?1% OR c.username LIKE %?1%")
    List<CrittrUser> searchByEmail(String email);

    @Query("SELECT c FROM CrittrUser c WHERE c.role = ?1")
    List<CrittrUser> listByRole(UserRoleStatus role);

}
