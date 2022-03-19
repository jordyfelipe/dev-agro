package com.devinhouse.devagro.mappers;

import com.devinhouse.devagro.dto.GrainInputDto;
import com.devinhouse.devagro.models.Grain;

public class GrainMapper {

    public static Grain mapGrain(GrainInputDto grainInputDto) {
        Grain grain = new Grain();
        grain.setName(grainInputDto.getName());
        grain.setAverageHarvestTime(grainInputDto.getAverageHarvestTime());
        return grain;
    }

}
