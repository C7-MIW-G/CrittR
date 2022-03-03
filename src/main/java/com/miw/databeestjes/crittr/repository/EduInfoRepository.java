package com.miw.databeestjes.crittr.repository;

import com.miw.databeestjes.crittr.model.EduInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EduInfoRepository extends JpaRepository<EduInfo, String> {

    @Query("SELECT ei FROM EduInfo ei WHERE ei.eduInfoSpecies = ?1")
    List<EduInfo> findEduInfoBySpecies(String species);

}
