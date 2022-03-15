package com.devinhouse.devagro.repositories;

import com.devinhouse.devagro.models.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    @Query(value = "SELECT * FROM tb_farm AS farm WHERE farm.company_id = :companyId",nativeQuery = true)
    List<Farm> findFarmsByCompanyId(@Param("companyId") Long companyId);

    @Query(value = "SELECT COUNT(tb_farm.id) AS count FROM tb_farm WHERE company_id = :companyId",nativeQuery = true)
    Integer countFarmsByCompanyId(@Param("companyId") Long companyId);


}
