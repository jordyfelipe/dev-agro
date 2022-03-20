package com.devinhouse.devagro.controllers;

import com.devinhouse.devagro.dto.FarmInputDto;
import com.devinhouse.devagro.dto.FarmNextHarvestOutputDto;
import com.devinhouse.devagro.dto.GrainStockOutputDto;
import com.devinhouse.devagro.models.Farm;
import com.devinhouse.devagro.services.FarmService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/farm")
public class FarmController {

    private FarmService service;

    public FarmController(FarmService service) {
        this.service = service;
    }

    /**
     * Retorna as fazendas cadastrados na base de dados.
     *
     * @return Lista de fazendas.
     */
    @GetMapping("/list")
    public List<Farm> findAll() {
        return service.findAll();
    }

    /**
     * Cadastra uma nova fazenda na base de dados.
     *
     * @param farmInputDto DTO da fazenda a ser cadastrada.
     * @return Fazenda cadastrada.
     */
    @PostMapping
    public Farm insert(@RequestBody @Valid FarmInputDto farmInputDto) {
        return service.insert(farmInputDto);
    }

    /**
     * Registra a retirada de grãos do estoque da fazenda.
     *
     * @param id    Id da fazenda.
     * @param value Quantidade de grãos a ser retirada.
     * @return Fazenda com estoque de grãos atualizado.
     */
    @PostMapping("/draw/{id}")
    public Farm drawGrainStock(@PathVariable Long id, @RequestParam(value = "value") Double value) {
        return service.drawGrainStock(id, value);
    }

    /**
     * Registra a colheita de grãos de uma fazenda e insere no estoque.
     *
     * @param id    Id da fazenda.
     * @param value Quantidade de grãos a ser depositada no estoque.
     * @return Fazenda com estoque de grãos atualizado.
     */
    @PostMapping("/harvest/{id}")
    public Farm depositGrainStock(@PathVariable Long id, @RequestParam(value = "value") Double value) {
        return service.depositGrainStock(id, value);
    }

    /**
     * Atualiza uma fazenda na base de dados.
     *
     * @param id           Id da fazenda a ser atualizada.
     * @param farmInputDto DTO da fazenda a ser atualizada.
     * @return Fazenda atualizada.
     */
    @PutMapping("/{id}")
    public Farm update(
            @PathVariable Long id,
            @RequestBody @Valid FarmInputDto farmInputDto) {
        return service.update(id, farmInputDto);
    }

    /**
     * Deleta uma Fazenda na base de dados.
     *
     * @param id Id da Fazenda.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    /**
     * Retorna as fazendas de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return Lista com fazendas da empresa.
     */
    @GetMapping("/farms-by-company")
    public List<Farm> findFarmsByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        return service.findFarmsByCompanyId(companyId);
    }

    /**
     * Consulta a quantidade de fazendas de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return Quantidade de fazendas.
     */
    @GetMapping("/quantity-by-company")
    public Integer countFarmsByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        return service.countFarmsByCompanyId(companyId);
    }

    /**
     * Retorna a data da próxima colheita das fazendas de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return Id e nome das dazendas de uma empresa juntamente com a data da próxima colheita de cada fazenda.
     */
    @GetMapping("/list-farm-next-harvest")
    public List<FarmNextHarvestOutputDto> listFarmsNextHarvest(@RequestParam(value = "companyId") Long companyId) {
        return service.listFarmsNextHarvest(companyId);
    }

    /**
     * Consulta o estoque de grãos das fazendas de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return nome e quantidade em estoque de cada grão de uma empresa.
     */
    @GetMapping("/list-grain-stock-by-company")
    public List<GrainStockOutputDto> listGrainStockByCompany(@RequestParam(value = "companyId") Long companyId) {
        return service.listGrainStockByCompany(companyId);
    }
}
