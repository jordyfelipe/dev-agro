package com.devinhouse.devagro.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class FarmNextHarvestOutputDto {

    @NotBlank
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private LocalDate nextHarvest;

}
