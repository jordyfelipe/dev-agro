package com.devinhouse.devagro.dto;

import com.devinhouse.devagro.models.Company;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CompanyDto {

    @NotBlank
    private String name;
    @NotBlank
    private String cnpj;
    @NotBlank
    private String address;

    public Company convert(){
        Company company = new Company();
        company.setName(name);
        company.setCnpj(cnpj);
        company.setAddress(address);
        return company;
    }
}
