package com.devinhouse.devagro.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeInputDto {

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
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private LocalDate hiringDate;
    @NotNull
    private Long companyId;

}
