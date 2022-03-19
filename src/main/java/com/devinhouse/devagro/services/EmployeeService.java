package com.devinhouse.devagro.services;

import com.devinhouse.devagro.dto.EmployeeInputDto;
import com.devinhouse.devagro.mappers.EmployeeMapper;
import com.devinhouse.devagro.models.Company;
import com.devinhouse.devagro.models.Employee;
import com.devinhouse.devagro.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;

    public EmployeeService(EmployeeRepository employeeRepository,
                           CompanyService companyService) {
        this.employeeRepository = employeeRepository;
        this.companyService = companyService;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee insert(EmployeeInputDto employeeInputDto) {
        Employee employee = EmployeeMapper.mapEmployee(employeeInputDto);
        addCompanyById(employee, employeeInputDto.getCompanyId());
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, EmployeeInputDto employeeInputDto) {
        Employee updatedEmployee = employeeRepository.findById(id).get();
        updatedEmployee.setName(employeeInputDto.getName());
        updatedEmployee.setLastName(employeeInputDto.getLastName());
        updatedEmployee.setCpf(employeeInputDto.getCpf());
        updatedEmployee.setAddress(employeeInputDto.getAddress());
        updatedEmployee.setTelephoneNumber(employeeInputDto.getTelephoneNumber());
        updatedEmployee.setGender(employeeInputDto.getGender());
        updatedEmployee.setBirthDate(employeeInputDto.getBirthDate());
        updatedEmployee.setHiringDate(employeeInputDto.getHiringDate());
        addCompanyById(updatedEmployee, employeeInputDto.getCompanyId());
        return employeeRepository.save(updatedEmployee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public void addCompanyById(Employee employee, Long companyId) {
        Optional<Company> company = companyService.findById(companyId);
        if (company.isPresent())
            employee.setCompany(company.get());
        else
            System.out.println("Company not found!");
    }

    public Integer countEmployeesByCompanyId(Long companyId) {
        return employeeRepository.counEmployeesByCompanyId(companyId);
    }

    public List<Employee> findEmployeesByCompanyId(Long companyId) {
        return employeeRepository.findEmployeesByCompanyId(companyId);
    }
}
