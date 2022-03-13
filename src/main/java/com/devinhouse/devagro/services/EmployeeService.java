package com.devinhouse.devagro.services;

import com.devinhouse.devagro.dto.EmployeeDto;
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

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee insert(EmployeeDto employeeDto){
        Employee employee = employeeDto.convert();
        addCompanyById(employee,employeeDto.getCompanyId());
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, EmployeeDto employeeDto){
        Employee updatedEmployee = employeeRepository.findById(id).get();
        updatedEmployee.setName(employeeDto.getName());
        updatedEmployee.setLastName(employeeDto.getLastName());
        updatedEmployee.setCpf(employeeDto.getCpf());
        updatedEmployee.setAddress(employeeDto.getAddress());
        updatedEmployee.setTelephoneNumber(employeeDto.getTelephoneNumber());
        updatedEmployee.setGender(employeeDto.getGender());
        updatedEmployee.setBirthDate(employeeDto.getBirthDate());
        updatedEmployee.setHiringDate(employeeDto.getHiringDate());
        addCompanyById(updatedEmployee,employeeDto.getCompanyId());
        return employeeRepository.save(updatedEmployee);
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
    }

    public void addCompanyById(Employee employee, Long companyId){
        Optional<Company> company = companyService.findById(companyId);
        if(company.isPresent())
             employee.setCompany(company.get());
        else
            System.out.println("Company not found!");
    }
}
