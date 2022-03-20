package com.devinhouse.devagro.services;

import com.devinhouse.devagro.dto.FarmInputDto;
import com.devinhouse.devagro.dto.FarmNextHarvestOutputDto;
import com.devinhouse.devagro.dto.GrainStockOutputDto;
import com.devinhouse.devagro.mappers.FarmMapper;
import com.devinhouse.devagro.models.Company;
import com.devinhouse.devagro.models.Farm;
import com.devinhouse.devagro.models.Grain;
import com.devinhouse.devagro.repositories.FarmRepository;
import com.devinhouse.devagro.services.exceptions.EntityNotFoundException;
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

    public Farm update(Long id, FarmInputDto farmInputDto) {
        Farm updatedFarm = farmRepository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
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

    /**
     * Registra a retirada de grãos do estoque da fazenda.
     *
     * @param id    Id da fazenda.
     * @param value Quantidade de grãos a ser retirada.
     * @return Fazenda com estoque de grãos atualizado.
     */
    public Farm drawGrainStock(Long id, Double value) {
        Farm updatedFarm = farmRepository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
        updatedFarm.setStock(updatedFarm.getStock() - value);
        if (updatedFarm.getStock() < 0) {
            updatedFarm.setStock(0.0);
        }
        farmRepository.save(updatedFarm);
        return updatedFarm;
    }

    /**
     * Registra a colheita de grãos de uma fazenda e insere no estoque.
     *
     * @param id    Id da fazenda.
     * @param value Quantidade de grãos a ser depositada no estoque.
     * @return Fazenda com estoque de grãos atualizado.
     */
    public Farm depositGrainStock(Long id, Double value) {
        Farm updatedFarm = farmRepository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
        updatedFarm.setStock(updatedFarm.getStock() + value);
        updatedFarm.setLastHarvest(LocalDate.now());
        farmRepository.save(updatedFarm);
        return updatedFarm;
    }

    /**
     * Localiza uma empresa pelo Id e vincula a uma fazenda.
     *
     * @param farm      Instância de uma fazenda.
     * @param companyId Id da empresa a ser vinculada na fazenda.
     */
    public void addCompanyById(Farm farm, Long companyId) {
        Company company = companyService.findById(companyId);
        farm.setCompany(company);
    }

    /**
     * Localiza um grão pelo Id e vincula a uma fazenda.
     *
     * @param farm    Instância de uma fazenda.
     * @param grainId Id do grão a ser vinculado na fazenda.
     */
    private void addGrainById(Farm farm, Long grainId) {
        Grain grain = grainService.findById(grainId);
        farm.setGrain(grain);
    }

    /**
     * Retorna uma lista das fazendas de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return Lista de fazendas de uma empresa.
     */
    public List<Farm> findFarmsByCompanyId(Long companyId) {
        companyService.findById(companyId);
        return farmRepository.findFarmsByCompanyId(companyId);
    }

    /**
     * Retorna a quantidade de fazendas de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return Retorna a quantidade de fazendas de uma empresa.
     */
    public Integer countFarmsByCompanyId(Long companyId) {
        companyService.findById(companyId);
        return farmRepository.countFarmsByCompanyId(companyId);
    }

    /**
     * Retorna a data da próxima colheita das fazendas de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return Id e nome das dazendas de uma empresa juntamente com a data da próxima colheita de cada fazenda.
     */
    public List<FarmNextHarvestOutputDto> listFarmsNextHarvest(Long companyId) {
        companyService.findById(companyId);
        List<FarmNextHarvestOutputDto> list = new ArrayList<>();
        for (Farm farm : farmRepository.findAll()) {
            FarmNextHarvestOutputDto farmNextHarvestOutputDto = FarmMapper.mapFarmNextHarvestDto(farm);
            farmNextHarvestOutputDto.setNextHarvest(nextHarvestCalculator(farm.getLastHarvest(), farm.getGrain().getAverageHarvestTime()));
            list.add(farmNextHarvestOutputDto);
        }
        return list;
    }

    /**
     * Calcula a data da próxima colheita.
     *
     * @param lastHarvest        Data da ultima colheita.
     * @param averageHarvestTime Tempo médio de colheita de um grão.
     * @return Data da próxima colheita.
     */
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
     * Lista os grãos de uma empresa juntamente com o total de estoque por grão.
     *
     * @param companyId Id da empresa.
     * @return Lista com nome e quantidade em estoque dos grãos de uma empresa.
     */
    public List<GrainStockOutputDto> listGrainStockByCompany(Long companyId) {
        companyService.findById(companyId);
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
     * Armazena em uma lista os Ids de grãos associados a fazendas.
     *
     * @param farmList Lista de fazendas
     * @return Retorna uma lista de ids de grãos.
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
