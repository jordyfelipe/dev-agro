package com.devinhouse.devagro.dto;

import com.devinhouse.devagro.models.Farm;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FarmDto {

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private Long companyId;
    @NotBlank
    private Long grainId;

    public Farm convert(){
        Farm farm = new Farm();
        farm.setName(name);
        farm.setAddress(address);
        return farm;
    }
}
