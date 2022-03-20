package com.devinhouse.devagro.controllers;

import com.devinhouse.devagro.dto.EmployeeInputDto;
import com.devinhouse.devagro.models.Employee;
import com.devinhouse.devagro.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    /**
     * Lista as funcionarios cadastrados na base de dados.
     *
     * @return Lista de funcionarios.
     */
    @GetMapping("/list")
    public List<Employee> findAll() {
        return service.findAll();
    }

    /**
     * Cadastra um novo funcionário na base de dados.
     *
     * @param employeeInputDto DTO do funcionário a ser cadastrado.
     * @return Funcionário cadastrado.
     */
    @PostMapping
    public Employee insert(@RequestBody @Valid EmployeeInputDto employeeInputDto) {
        return service.insert(employeeInputDto);
    }

    /**
     * Atualiza um funcionário na base de dados.
     *
     * @param id               Id da empresa a ser atualizada.
     * @param employeeInputDto DTO da empresa a ser atualizada.
     * @return Funcionário atualizado.
     */
    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Long id,
            @RequestBody @Valid EmployeeInputDto employeeInputDto) {
        return service.update(id, employeeInputDto);
    }

    /**
     * Deleta um Funcionario na base de dados
     *
     * @param id Id do Funcionário
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    /**
     * Consulta a quantidade de funcionário de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return quantidade de funcionários.
     */
    @GetMapping("/quantity-by-company")
    public Integer countEmployeesByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        return service.countEmployeesByCompanyId(companyId);
    }

    /**
     * Lista todos os funcionários de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return Lista com funcionários cadastrados.
     */
    @GetMapping("/employees-by-company")
    public List<Employee> findEmployeesByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        return service.findEmployeesByCompanyId(companyId);
    }
}
