package com.devinhouse.devagro.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_grain")
public class Grain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "average_harvest_time")
    private int averageHarvestTime;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

}
