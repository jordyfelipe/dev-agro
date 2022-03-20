package com.devinhouse.devagro.services;

import com.devinhouse.devagro.models.Company;
import com.devinhouse.devagro.repositories.CompanyRepository;
import com.devinhouse.devagro.services.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public List<Company> findAll() {
        return repository.findAll();
    }

    public Company insert(Company company) {
        return repository.save(company);
    }

    public Company update(Long id, Company company) {
        Company updatedCompany = repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
        updatedCompany.setName(company.getName());
        updatedCompany.setCnpj(company.getCnpj());
        updatedCompany.setAddress(company.getAddress());
        return repository.save(updatedCompany);
    }

    public void delete(Long id) {
        repository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
        repository.deleteById(id);
    }

    public Company findById(Long companyId) {
        return repository.findById(companyId) //
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
    }

}
