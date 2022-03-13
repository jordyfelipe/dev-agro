package com.devinhouse.devagro.dto;

import com.devinhouse.devagro.models.Grain;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GrainDto {

    @NotBlank
    private String name;
    @NotBlank
    private int averageHarvestTime;
    @NotBlank
    private Long companyId;

    public Grain convert(){
        Grain grain = new Grain();
        grain.setName(name);
        grain.setAverageHarvestTime(averageHarvestTime);
        return grain;
    }

}
