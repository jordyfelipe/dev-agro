package com.devinhouse.devagro.controllers;

import com.devinhouse.devagro.dto.CompanyInputDto;
import com.devinhouse.devagro.mappers.CompanyMapper;
import com.devinhouse.devagro.models.Company;
import com.devinhouse.devagro.services.CompanyService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    /**
     * Retorna as empresas cadastradas na base de dados.
     *
     * @return Lista de empresas.
     */
    @GetMapping("/list")
    public List<Company> findAll() {
        return service.findAll();
    }

    /**
     * Cadastra uma nova empresa na base de dados.
     *
     * @param companyInputDto DTO da empresa a ser cadastrada.
     * @return Empresa cadastrada.
     */
    @PostMapping
    public Company insert(@RequestBody @Valid CompanyInputDto companyInputDto) {
        return service.insert(CompanyMapper.mapCompany(companyInputDto));
    }

    /**
     * Atualiza uma empresa na base de dados.
     *
     * @param companyInputDto DTO da empresa a ser atualizada.
     * @param id              Id da empresa a ser atualizada.
     * @return Empresa atualizada.
     */
    @PutMapping("/{id}")
    public Company update(
            @RequestBody @Valid CompanyInputDto companyInputDto, @PathVariable Long id) {
        return service.update(id, CompanyMapper.mapCompany(companyInputDto));
    }

    /**
     * Deleta uma empresa da base de dados.
     *
     * @param id Id da empresa a der deletada.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
