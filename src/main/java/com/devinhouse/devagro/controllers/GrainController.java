package com.devinhouse.devagro.controllers;

import com.devinhouse.devagro.dto.GrainInputDto;
import com.devinhouse.devagro.models.Grain;
import com.devinhouse.devagro.services.GrainService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grain")
public class GrainController {

    private GrainService service;

    public GrainController(GrainService service) {
        this.service = service;
    }

    /**
     * Lista os grãos cadastrados na base de dados.
     *
     * @return Lista de grãos.
     */
    @GetMapping("/list")
    public List<Grain> findAll() {
        return service.findAll();
    }

    /**
     * Cadastra um novo grãos na base de dados.
     *
     * @param grainInputDto DTO do grão a ser cadastrado.
     * @return Grão cadastrado.
     */
    @PostMapping
    public Grain insert(@RequestBody @Valid GrainInputDto grainInputDto) {
        return service.insert(grainInputDto);
    }

    /**
     * Atualiza um grão na base de dados.
     *
     * @param id            Id do grão.
     * @param grainInputDto DTO do grão a ser atualizado.
     * @return Grão atualizado.
     */
    @PutMapping("/{id}")
    public Grain update(
            @PathVariable Long id,
            @RequestBody @Valid GrainInputDto grainInputDto) {
        return service.update(id, grainInputDto);
    }

    /**
     * Deleta uma Fazenda na base de dados.
     *
     * @param id Id do grão.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    /**
     * Consulta a quantidade de grãos de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return Quantidade de grãos.
     */
    @GetMapping("/quantity-by-company")
    public Integer countFarmsByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        return service.countGrainsByCompanyId(companyId);
    }

    /**
     * Retorna os grãos de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return Lista com grãos de uma empresa.
     */
    @GetMapping("/grains-by-company")
    public List<Grain> findGrainsByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        return service.findGrainsByCompanyId(companyId);
    }
}
