package com.devinhouse.devagro.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class FarmInputDto {

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotNull
    private Long companyId;
    @NotNull
    private Long grainId;
    @NotNull
    private LocalDate lastHarvest;
    @NotNull
    private Double stock;

}
