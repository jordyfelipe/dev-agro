package com.devinhouse.devagro.controllers;

import com.devinhouse.devagro.dto.FarmDto;
import com.devinhouse.devagro.models.Farm;
import com.devinhouse.devagro.services.FarmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farm")
public class FarmController {

    private FarmService service;

    public FarmController(FarmService service){
        this.service = service;
    }

    @GetMapping("/list")
    public List<Farm> findAll(){
        return service.findAll();
    }

    @PostMapping
    public Farm insert(@RequestBody FarmDto farmDto){
        return service.insert(farmDto);
    }

    @PutMapping("/{id}")
    public Farm update(
            @PathVariable Long id,
            @RequestBody FarmDto farmDto){
        return service.update(id, farmDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("/by-company")
    public List<Farm> findFarmsByCompanyId(@RequestParam(value = "companyId") Long companyId){
        return service.findFarmsByCompanyId(companyId);
    }

    @GetMapping("/quantity-by-company")
    public Integer countFarmsByCompanyId(@RequestParam(value = "companyId") Long companyId){
        return service.countFarmsByCompanyId(companyId);
    }

}
