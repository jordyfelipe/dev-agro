package com.devinhouse.devagro.repositories;

import com.devinhouse.devagro.models.Grain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrainRepository extends JpaRepository<Grain, Long> {

    @Query(value = "SELECT * FROM tb_grain AS grain WHERE grain.company_id = :companyId", nativeQuery = true)
    List<Grain> findGrainsByCompanyId(@Param("companyId") Long companyId);

    @Query(value = "SELECT COUNT(tb_grain.id) AS count FROM tb_grain WHERE company_id = :companyId", nativeQuery = true)
    Integer countFarmsByCompanyId(@Param("companyId") Long companyId);

    @Query(value = "SELECT name FROM tb_grain AS grain WHERE grain.id = :grainId", nativeQuery = true)
    String grainNameById(Long grainId);

}
