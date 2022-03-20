package com.devinhouse.devagro.repositories;

import com.devinhouse.devagro.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM tb_employee AS employee WHERE employee.company_id = :companyId", nativeQuery = true)
    List<Employee> findEmployeesByCompanyId(@Param("companyId") Long companyId);

    @Query(value = "SELECT COUNT(tb_employee.id) AS count FROM tb_employee WHERE company_id = :companyId", nativeQuery = true)
    Integer countEmployeesByCompanyId(@Param("companyId") Long companyId);
}
