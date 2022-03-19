package com.devinhouse.devagro.controllers;

import com.devinhouse.devagro.dto.CompanyInputDto;
import com.devinhouse.devagro.mappers.CompanyMapper;
import com.devinhouse.devagro.models.Company;
import com.devinhouse.devagro.services.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public List<Company> findAll() {
        return service.findAll();
    }

    @PostMapping
    public Company insert(@RequestBody CompanyInputDto companyInputDto) {
        return service.insert(CompanyMapper.mapCompany(companyInputDto));
    }

    @PutMapping("/{id}")
    public Company update(
            @PathVariable Long id,
            @RequestBody CompanyInputDto companyInputDto) {
        return service.update(id, CompanyMapper.mapCompany(companyInputDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
