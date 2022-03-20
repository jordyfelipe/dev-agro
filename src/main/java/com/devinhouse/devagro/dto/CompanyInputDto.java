package com.devinhouse.devagro.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CompanyInputDto {

    @NotBlank
    private String name;
    @NotBlank
    @Pattern(regexp = "(^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$)", message = "CNPJ informado inválido")
    private String cnpj;
    @NotBlank
    private String address;

}
