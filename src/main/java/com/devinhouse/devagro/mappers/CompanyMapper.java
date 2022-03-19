package com.devinhouse.devagro.mappers;

import com.devinhouse.devagro.dto.CompanyInputDto;
import com.devinhouse.devagro.models.Company;

public class CompanyMapper {

    public static Company mapCompany(CompanyInputDto companyInputDto) {
        Company company = new Company();
        company.setName(companyInputDto.getName());
        company.setCnpj(companyInputDto.getCnpj());
        company.setAddress(companyInputDto.getAddress());
        return company;
    }

}
