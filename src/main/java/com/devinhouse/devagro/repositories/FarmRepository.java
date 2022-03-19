package com.devinhouse.devagro.repositories;

import com.devinhouse.devagro.models.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    @Query(value = "SELECT * FROM tb_farm AS farm WHERE farm.company_id = :companyId", nativeQuery = true)
    List<Farm> findFarmsByCompanyId(@Param("companyId") Long companyId);

    @Query(value = "SELECT COUNT(tb_farm.id) AS count FROM tb_farm WHERE company_id = :companyId", nativeQuery = true)
    Integer countFarmsByCompanyId(@Param("companyId") Long companyId);

//    @Query(value = "SELECT tbg.name, SUM(tbf.stock) AS stock FROM tb_farm AS tbf\n" +
//            "INNER JOIN tb_grain AS tbg\n" +
//            "ON tbf.company_id = :companyId AND tbg.company_id = :companyId\n" +
//            "WHERE tbf.grain_id = tbg.id\n" +
//            "GROUP BY tbg.name\n" +
//            "ORDER BY stock",nativeQuery = true)
//    List<ResultSet> listGrainStockByCompany(@Param("companyId") Long companyId);

}
