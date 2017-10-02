package com.sdr.dto;

import com.sdr.dto.base.EntityDTO;
import com.sdr.entity.Company;

/**
 * Created by Konstantin on 29.09.2017.
 */
public class CarTypeDTO extends EntityDTO {

    private String name;

    public CarTypeDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
