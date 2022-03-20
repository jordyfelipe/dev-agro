package com.devinhouse.devagro.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeInputDto {

    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    @Pattern(regexp = "(^(\\d{3}.\\d{3}.\\d{3}-\\d{2}))", message = "CPF informado inválido")
    private String cpf;
    @NotBlank
    private String address;
    @NotBlank
    @Pattern(regexp = "^\\(\\d{2}\\)\\d{5}\\d{4}$", message = "Número de telefone inválido")
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
