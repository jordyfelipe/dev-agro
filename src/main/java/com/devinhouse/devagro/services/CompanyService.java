package com.devinhouse.devagro.services;

import com.devinhouse.devagro.models.Company;
import com.devinhouse.devagro.repositories.CompanyRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> findAll(){
        return repository.findAll();
    }

    public Company insert(Company company){
        return repository.save(company);
    }

    public Company update(Long id, Company company){
        Company updatedCompany = repository.findById(id).get();
        updatedCompany.setName(company.getName());
        updatedCompany.setCnpj(company.getCnpj());
        updatedCompany.setAddress(company.getAddress());
        return repository.save(updatedCompany);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Optional<Company> findById(Long companyId){
        return repository.findById(companyId);
    }

}
