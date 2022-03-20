package com.devinhouse.devagro.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GrainInputDto {

    @NotBlank
    private String name;
    @NotNull
    private int averageHarvestTime;
    @NotNull
    private Long companyId;

}
