package com.devinhouse.devagro.controllers;

import com.devinhouse.devagro.dto.EmployeeDto;
import com.devinhouse.devagro.models.Employee;
import com.devinhouse.devagro.services.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @GetMapping("/list")
    public List<Employee> findAll(){
        return service.findAll();
    }

    @PostMapping
    public Employee insert(@RequestBody EmployeeDto employeeDto){
        return service.insert(employeeDto);
    }

    @PutMapping("/{id}")
    public Employee update(
            @PathVariable Long id,
            @RequestBody EmployeeDto employeeDto){
        return service.update(id, employeeDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
