package com.devinhouse.devagro.services;

import com.devinhouse.devagro.dto.EmployeeInputDto;
import com.devinhouse.devagro.mappers.EmployeeMapper;
import com.devinhouse.devagro.models.Company;
import com.devinhouse.devagro.models.Employee;
import com.devinhouse.devagro.repositories.EmployeeRepository;
import com.devinhouse.devagro.services.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Employee updatedEmployee = employeeRepository.findById(id) //
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada"));
        ;
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

    /**
     * Localiza uma empresa pelo Id e vincula a um funcionário.
     *
     * @param employee  Instância de um funcionário.
     * @param companyId Id da empresa.
     */
    public void addCompanyById(Employee employee, Long companyId) {
        Company company = companyService.findById(companyId);
        employee.setCompany(company);
    }

    /**
     * Retorna a quantidade de funcionários de uma empresa.
     *
     * @param companyId Id da empresa.
     * @return Quantidade de funcionários de uma empresa.
     */
    public Integer countEmployeesByCompanyId(Long companyId) {
        companyService.findById(companyId);
        employeeRepository.countEmployeesByCompanyId(companyId);
        return employeeRepository.countEmployeesByCompanyId(companyId);
    }

    /**
     * Retorna uma lista de funcionários de uma empresa.
     *
     * @param companyId ID da empresa.
     * @return Lista de funcionários de uma empresa.
     */
    public List<Employee> findEmployeesByCompanyId(Long companyId) {
        companyService.findById(companyId);
        return employeeRepository.findEmployeesByCompanyId(companyId);
    }
}
