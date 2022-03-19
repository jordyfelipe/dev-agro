package com.devinhouse.devagro.services;

import com.devinhouse.devagro.dto.FarmInputDto;
import com.devinhouse.devagro.dto.FarmNextHarvestOutputDto;
import com.devinhouse.devagro.dto.GrainStockOutputDto;
import com.devinhouse.devagro.mappers.FarmMapper;
import com.devinhouse.devagro.models.Company;
import com.devinhouse.devagro.models.Farm;
import com.devinhouse.devagro.models.Grain;
import com.devinhouse.devagro.repositories.FarmRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    public List<Farm> findAll() {
        return farmRepository.findAll();
    }

    public Farm insert(FarmInputDto farmInputDto) {
        Farm farm = FarmMapper.mapFarm(farmInputDto);
        addCompanyById(farm, farmInputDto.getCompanyId());
        addGrainById(farm, farmInputDto.getGrainId());
        return farmRepository.save(farm);
    }

    public Farm drawGrainStock(Long id, Double value) {
        Farm updatedFarm = farmRepository.findById(id).get();
        updatedFarm.setStock(updatedFarm.getStock() - value);
        if (updatedFarm.getStock() < 0) {
            updatedFarm.setStock(0.0);
        }
        farmRepository.save(updatedFarm);
        return updatedFarm;
    }

    public Farm depositGrainStock(Long id, Double value) {
        Farm updatedFarm = farmRepository.findById(id).get();
        updatedFarm.setStock(updatedFarm.getStock() + value);
        updatedFarm.setLastHarvest(LocalDate.now());
        farmRepository.save(updatedFarm);
        return updatedFarm;
    }

    public Farm update(Long id, FarmInputDto farmInputDto) {
        Farm updatedFarm = farmRepository.findById(id).get();
        updatedFarm.setName(farmInputDto.getName());
        updatedFarm.setAddress(farmInputDto.getAddress());
        updatedFarm.setStock(farmInputDto.getStock());
        addCompanyById(updatedFarm, farmInputDto.getCompanyId());
        addGrainById(updatedFarm, farmInputDto.getGrainId());
        return farmRepository.save(updatedFarm);
    }

    public void delete(Long id) {
        farmRepository.deleteById(id);
    }

    public void addCompanyById(Farm farm, Long companyId) {
        Optional<Company> company = companyService.findById(companyId);
        if (company.isPresent())
            farm.setCompany(company.get());
        else
            System.out.println("Company not found!");
    }

    private void addGrainById(Farm farm, Long grainId) {
        Optional<Grain> grain = grainService.findById(grainId);
        if (grain.isPresent())
            farm.setGrain(grain.get());
        else
            System.out.println("Company not found!");
    }

    public List<Farm> findFarmsByCompanyId(Long companyId) {
        return farmRepository.findFarmsByCompanyId(companyId);
    }

    public Integer countFarmsByCompanyId(Long companyId) {
        return farmRepository.countFarmsByCompanyId(companyId);
    }

    public List<FarmNextHarvestOutputDto> listFarmsNextHarvest(Long companyId) {
        List<FarmNextHarvestOutputDto> list = new ArrayList<>();

        for (Farm farm : farmRepository.findAll()) {
            FarmNextHarvestOutputDto farmNextHarvestOutputDto = FarmMapper.mapFarmNextHarvestDto(farm);
            if (farmNextHarvestOutputDto.getLastHarvest() == null) {
                farmNextHarvestOutputDto.setLastHarvest(LocalDate.now());
            }
            farmNextHarvestOutputDto.setNextHarvest(nextHarvestCalculator(farm.getLastHarvest(), farm.getGrain().getAverageHarvestTime()));
            farmNextHarvestOutputDto.setLastHarvest(LocalDate.now());
            list.add(farmNextHarvestOutputDto);
        }
        return list;
    }

    private LocalDate nextHarvestCalculator(LocalDate lastHarvest, int averageHarvestTime) {
        LocalDate nextHarvest;
        if (lastHarvest == null) {
            nextHarvest = LocalDate.now().plusDays(averageHarvestTime);
        } else {
            nextHarvest = lastHarvest.plusDays(averageHarvestTime);
        }
        return nextHarvest;
    }

    /**
     * @param companyId
     * @return Retorna uma lista de grãos por empresa juntamente com o total de estoque por grão.
     */
    public List<GrainStockOutputDto> listGrainStockByCompany(Long companyId) {
        List<Farm> farmList = farmRepository.findFarmsByCompanyId(companyId);
        List<GrainStockOutputDto> grainStockOutputDtoList = new ArrayList<>();
        Double totalStock = 0.0;

        for (Long id : listGrainIdByFarmList(farmList)) {
            for (Farm farm : farmList) {
                if (farm.getGrain().getId() == id) {
                    totalStock += farm.getStock();
                }
            }
            GrainStockOutputDto grainStockOutputDto = new GrainStockOutputDto();
            grainStockOutputDto.setName(grainService.grainNameById(id));
            grainStockOutputDto.setStock(totalStock);
            grainStockOutputDtoList.add(grainStockOutputDto);
            totalStock = 0.0;
        }
        Collections.sort(grainStockOutputDtoList);
        return grainStockOutputDtoList;
    }

    /**
     * @param farmList
     * @return Retorna uma lista de ids de grãos associados a fazendas.
     */
    private List<Long> listGrainIdByFarmList(List<Farm> farmList) {
        List<Long> idGrains = new ArrayList<>();
        for (Farm farm : farmList) {
            if (!idGrains.contains(farm.getGrain().getId()))
                idGrains.add(farm.getGrain().getId());
        }
        return idGrains;
    }
}
