package com.devinhouse.devagro.dto;

import com.devinhouse.devagro.models.Employee;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto {

    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String cpf;
    @NotBlank
    private String address;
    @NotBlank
    private String telephoneNumber;
    @NotBlank
    private String gender;
    @NotBlank
    private LocalDate birthDate;
    @NotBlank
    private LocalDate hiringDate;
    @NotBlank
    private Long companyId;

    public Employee convert(){
        Employee employee = new Employee();
        employee.setName(name);
        employee.setLastName(lastName);
        employee.setCpf(cpf);
        employee.setAddress(address);
        employee.setTelephoneNumber(telephoneNumber);
        employee.setGender(gender);
        employee.setBirthDate(birthDate);
        employee.setHiringDate(hiringDate);
        return employee;
    }

}
