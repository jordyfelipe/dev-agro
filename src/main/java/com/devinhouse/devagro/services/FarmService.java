package com.devinhouse.devagro.services;

import com.devinhouse.devagro.dto.FarmDto;
import com.devinhouse.devagro.models.Company;
import com.devinhouse.devagro.models.Farm;
import com.devinhouse.devagro.models.Grain;
import com.devinhouse.devagro.repositories.FarmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FarmService {

    private final FarmRepository farmRepository;
    private final CompanyService companyService;
    private final GrainService grainService;

    public FarmService(FarmRepository farmRepository, CompanyService companyService, GrainService grainService) {
        this.farmRepository = farmRepository;
        this.companyService = companyService;
        this.grainService = grainService;
    }

    public List<Farm> findAll(){
        return farmRepository.findAll();
    }

    public Farm insert(FarmDto farmDto){
        Farm farm = farmDto.convert();
        addCompanyById(farm,farmDto.getCompanyId());
        addGrainById(farm,farmDto.getGrainId());
        return farmRepository.save(farm);
    }

    public Farm update(Long id, FarmDto farmDto){
        Farm updatedFarm = farmRepository.findById(id).get();
        updatedFarm.setName(farmDto.getName());
        updatedFarm.setAddress(farmDto.getAddress());
        addCompanyById(updatedFarm,farmDto.getCompanyId());
        addGrainById(updatedFarm,farmDto.getGrainId());
        return farmRepository.save(updatedFarm);
    }

    public void delete(Long id){
        farmRepository.deleteById(id);
    }

    public void addCompanyById(Farm farm, Long companyId){
        Optional<Company> company = companyService.findById(companyId);
        if(company.isPresent())
            farm.setCompany(company.get());
        else
            System.out.println("Company not found!");
    }

    private void addGrainById(Farm farm, Long grainId) {
        Optional<Grain> grain = grainService.findById(grainId);
        if(grain.isPresent())
            farm.setGrain(grain.get());
        else
            System.out.println("Company not found!");
    }

    public List<Farm> findFarmsByCompanyId(Long companyId){
        return farmRepository.findFarmsByCompanyId(companyId);
    }

    public Integer countFarmsByCompanyId(Long companyId){
        return farmRepository.countFarmsByCompanyId(companyId);
    }

}
