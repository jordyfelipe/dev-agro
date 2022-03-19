package com.devinhouse.devagro.mappers;

import com.devinhouse.devagro.dto.FarmInputDto;
import com.devinhouse.devagro.dto.FarmNextHarvestOutputDto;
import com.devinhouse.devagro.dto.GrainStockOutputDto;
import com.devinhouse.devagro.models.Farm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FarmMapper {

    public static FarmNextHarvestOutputDto mapFarmNextHarvestDto(Farm farm) {
        FarmNextHarvestOutputDto farmNextHarvestOutputDto = new FarmNextHarvestOutputDto();
        farmNextHarvestOutputDto.setName(farm.getName());
        farmNextHarvestOutputDto.setLastHarvest(farm.getLastHarvest());
        return farmNextHarvestOutputDto;
    }

    public static Farm mapFarm(FarmInputDto farmInputDto){
        Farm farm = new Farm();
        farm.setName(farmInputDto.getName());
        farm.setAddress(farmInputDto.getAddress());
        farm.setStock(farmInputDto.getStock());
        farm.setLastHarvest(farmInputDto.getLastHarvest());
        return farm;
    }

}
