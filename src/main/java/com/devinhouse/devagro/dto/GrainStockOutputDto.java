package com.devinhouse.devagro.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class GrainStockOutputDto implements Comparable<GrainStockOutputDto> {

    @NotBlank
    private String name;
    @NotNull
    private Double stock;

    @Override
    public int compareTo(GrainStockOutputDto outroGrainStockOutputDto) {
        if (this.stock > outroGrainStockOutputDto.getStock()) {
            return 1;
        }
        if (this.stock < outroGrainStockOutputDto.getStock()) {
            return -1;
        }
        return 0;
    }

}
