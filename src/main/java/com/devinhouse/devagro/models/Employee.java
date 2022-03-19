package com.devinhouse.devagro.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String cpf;
    private String address;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    private String gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "hiring_date")
    private LocalDate hiringDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
