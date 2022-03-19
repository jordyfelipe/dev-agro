package com.devinhouse.devagro.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CompanyInputDto {

    @NotBlank
    private String name;
    @NotBlank
    private String cnpj;
    @NotBlank
    private String address;

}
