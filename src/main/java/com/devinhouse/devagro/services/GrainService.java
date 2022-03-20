package com.devinhouse.devagro.services;

import com.devinhouse.devagro.dto.GrainInputDto;
import com.devinhouse.devagro.mappers.GrainMapper;
import com.devinhouse.devagro.models.Company;
import com.devinhouse.devagro.models.Grain;
import com.devinhouse.devagro.repositories.GrainRepository;
import com.devinhouse.devagro.services.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrainService {

    private final GrainRepository grainRepository;
    private final CompanyService companyService;

    public GrainService(GrainRepository repository, CompanyService companyService) {
        this.grainRepository = repository;
        this.companyService = companyService;
    }

    public List<Grain> findAll() {
        return grainRepository.findAll();
    }

    public Grain insert(GrainInputDto grainInputDto) {
        Grain grain = GrainMapper.mapGrain(grainInputDto);
        addCompanyById(grain, grainInputDto.getCompanyId());
        return grainRepository.save(grain);
    }

    public Grain update(Long id, GrainInputDto grainInputDto) {
        Grain updatedGrain = grainRepository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
        updatedGrain.setName(grainInputDto.getName());
        updatedGrain.setAverageHarvestTime(grainInputDto.getAverageHarvestTime());
        addCompanyById(updatedGrain, grainInputDto.getCompanyId());
        return grainRepository.save(updatedGrain);
    }

    public void delete(Long id) {
        grainRepository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
        grainRepository.deleteById(id);
    }

    public Grain findById(Long grainId) {
        return grainRepository.findById(grainId) //
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
    }

    /**
     * Localiza uma empresa pelo Id e vincula a um grão.
     *
     * @param grain     Instância do grão.
     * @param companyId Id da empresa.
     */
    public void addCompanyById(Grain grain, Long companyId) {
        Company company = companyService.findById(companyId);
        grain.setCompany(company);
    }

    /**
     * Retorna a quantidade de grãos de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return Quantidade de grãos de uma empresa.
     */
    public Integer countGrainsByCompanyId(Long companyId) {
        companyService.findById(companyId);
        return grainRepository.countFarmsByCompanyId(companyId);
    }

    /**
     * Retorna o nome de um grão através de seu Id.
     *
     * @param grainId Id do grão.
     * @return Nome do grão.
     */
    public String grainNameById(Long grainId) {
        grainRepository.findById(grainId) //
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
        return grainRepository.grainNameById(grainId);
    }

    /**
     * Retorna uma lista de grãos de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return Lista de grãos de uma empresa.
     */
    public List<Grain> findGrainsByCompanyId(Long companyId) {
        companyService.findById(companyId);
        return grainRepository.findGrainsByCompanyId(companyId);
    }
}
