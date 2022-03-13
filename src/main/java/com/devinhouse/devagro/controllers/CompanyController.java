package com.devinhouse.devagro.controllers;

import com.devinhouse.devagro.dto.CompanyDto;
import com.devinhouse.devagro.models.Company;
import com.devinhouse.devagro.services.CompanyService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService service;

    public CompanyController(CompanyService service){
        this.service = service;
    }

    @GetMapping("/list")
    public List<Company> findAll(){
        return service.findAll();
    }

    @PostMapping
    public Company insert(@RequestBody CompanyDto companyDto){
        return service.insert(companyDto.convert());
    }

    @PutMapping("/{id}")
    public Company update(
            @PathVariable Long id,
            @RequestBody CompanyDto companyDto){
        return service.update(id, companyDto.convert());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
