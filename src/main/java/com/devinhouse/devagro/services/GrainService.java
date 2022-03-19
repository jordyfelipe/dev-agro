package com.devinhouse.devagro.services;

import com.devinhouse.devagro.dto.GrainInputDto;
import com.devinhouse.devagro.mappers.GrainMapper;
import com.devinhouse.devagro.models.Company;
import com.devinhouse.devagro.models.Grain;
import com.devinhouse.devagro.repositories.GrainRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GrainService {

    private final GrainRepository grainRepository;
    private final CompanyService companyService;

    public GrainService(GrainRepository repository, CompanyService companyService) {
        this.grainRepository = repository;
        this.companyService = companyService;
    }

    public List<Grain> findAll(){
        return grainRepository.findAll();
    }

    public Grain insert(GrainInputDto grainInputDto){
        Grain grain = GrainMapper.mapGrain(grainInputDto);
        addCompanyById(grain, grainInputDto.getCompanyId());
        return grainRepository.save(grain);
    }

    public Grain update(Long id, GrainInputDto grainInputDto){
        Grain updatedGrain = grainRepository.findById(id).get();
        updatedGrain.setName(grainInputDto.getName());
        updatedGrain.setAverageHarvestTime(grainInputDto.getAverageHarvestTime());
        addCompanyById(updatedGrain, grainInputDto.getCompanyId());
        return grainRepository.save(updatedGrain);
    }

    public void delete(Long id){
        grainRepository.deleteById(id);
    }

    public void addCompanyById(Grain grain, Long companyId){
        Optional<Company> company = companyService.findById(companyId);
        if(company.isPresent())
            grain.setCompany(company.get());
        else
            System.out.println("Company not found!");
    }

    public Optional<Grain> findById(Long grainId) {
        return grainRepository.findById(grainId);
    }

    public Integer countGrainsByCompanyId(Long companyId){
        return grainRepository.countFarmsByCompanyId(companyId);
    }

    public List<Grain> findGrainsByCompanyId(Long companyId){
         return grainRepository.findGrainsByCompanyId(companyId);
    }

    public String grainNameById(Long grainId){
        return grainRepository.grainNameById(grainId);
    }
}
