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

    @GetMapping("/list")
    public List<Employee> findAll() {
        return service.findAll();
    }

    @PostMapping
    public Employee insert(@RequestBody @Valid EmployeeInputDto employeeInputDto) {
        return service.insert(employeeInputDto);
    }

    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Long id,
            @RequestBody EmployeeInputDto employeeInputDto) {
        return service.update(id, employeeInputDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/quantity-by-company")
    public Integer countEmployeesByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        return service.countEmployeesByCompanyId(companyId);
    }

    @GetMapping("/employees-by-company")
    public List<Employee> findEmployeesByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        return service.findEmployeesByCompanyId(companyId);
    }
}
