package com.devinhouse.devagro.controllers;

import com.devinhouse.devagro.dto.GrainInputDto;
import com.devinhouse.devagro.models.Grain;
import com.devinhouse.devagro.services.GrainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grain")
public class GrainController {

    private GrainService service;

    public GrainController(GrainService service){
        this.service = service;
    }

    @GetMapping("/list")
    public List<Grain> findAll(){
        return service.findAll();
    }

    @PostMapping
    public Grain insert(@RequestBody GrainInputDto grainInputDto){
        return service.insert(grainInputDto);
    }

    @PutMapping("/{id}")
    public Grain update(
            @PathVariable Long id,
            @RequestBody GrainInputDto grainInputDto){
        return service.update(id, grainInputDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/quantity-by-company")
    public Integer countFarmsByCompanyId(@RequestParam(value = "companyId") Long companyId){
        return service.countGrainsByCompanyId(companyId);
    }
}
