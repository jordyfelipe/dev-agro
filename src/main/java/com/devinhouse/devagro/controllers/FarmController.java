package com.devinhouse.devagro.controllers;

import com.devinhouse.devagro.dto.FarmInputDto;
import com.devinhouse.devagro.dto.FarmNextHarvestOutputDto;
import com.devinhouse.devagro.dto.GrainStockOutputDto;
import com.devinhouse.devagro.models.Farm;
import com.devinhouse.devagro.services.FarmService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/farm")
public class FarmController {

    private FarmService service;

    public FarmController(FarmService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public List<Farm> findAll() {
        return service.findAll();
    }

    @PostMapping
    public Farm insert(@RequestBody FarmInputDto farmInputDto) {
        return service.insert(farmInputDto);
    }

    @PostMapping("/draw/{id}")
    public Farm drawGrainStock(@PathVariable Long id, @RequestParam(value = "value") Double value) {
        return service.drawGrainStock(id, value);
    }

    @PostMapping("/harvest/{id}")
    public Farm depositGrainStock(@PathVariable Long id, @RequestParam(value = "value") Double value) {
        return service.depositGrainStock(id, value);
    }

    @PutMapping("/{id}")
    public Farm update(
            @PathVariable Long id,
            @RequestBody FarmInputDto farmInputDto) {
        return service.update(id, farmInputDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/farms-by-company")
    public List<Farm> findFarmsByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        return service.findFarmsByCompanyId(companyId);
    }

    @GetMapping("/quantity-by-company")
    public Integer countFarmsByCompanyId(@RequestParam(value = "companyId") Long companyId) {
        return service.countFarmsByCompanyId(companyId);
    }

    @GetMapping("/list-farm-next-harvest")
    public List<FarmNextHarvestOutputDto> listFarmsNextHarvest(@RequestParam(value = "companyId") Long companyId) {
        return service.listFarmsNextHarvest(companyId);
    }

    @GetMapping("/list-grain-stock-by-company")
    public List<GrainStockOutputDto> listGrainStockByCompany(@RequestParam(value = "companyId") Long companyId) throws SQLException {
        return service.listGrainStockByCompany(companyId);
    }
}
