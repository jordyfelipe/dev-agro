package com.devinhouse.devagro.mappers;

import com.devinhouse.devagro.dto.EmployeeInputDto;
import com.devinhouse.devagro.models.Employee;

public class EmployeeMapper {

    public static Employee mapEmployee(EmployeeInputDto employeeInputDto) {
        Employee employee = new Employee();
        employee.setName(employeeInputDto.getName());
        employee.setLastName(employeeInputDto.getLastName());
        employee.setCpf(employeeInputDto.getCpf());
        employee.setAddress(employeeInputDto.getAddress());
        employee.setTelephoneNumber(employeeInputDto.getTelephoneNumber());
        employee.setGender(employeeInputDto.getGender());
        employee.setBirthDate(employeeInputDto.getBirthDate());
        employee.setHiringDate(employeeInputDto.getHiringDate());
        return employee;
    }
}
