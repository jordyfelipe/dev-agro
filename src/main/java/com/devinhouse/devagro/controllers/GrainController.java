package com.devinhouse.devagro.controllers;

import com.devinhouse.devagro.dto.GrainDto;
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
    public Grain insert(@RequestBody GrainDto grainDto){
        return service.insert(grainDto);
    }

    @PutMapping("/{id}")
    public Grain update(
            @PathVariable Long id,
            @RequestBody GrainDto grainDto){
        return service.update(id, grainDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
