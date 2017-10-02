package com.sdr.dto;

import com.sdr.dto.base.EntityDTO;

/**
 * Created by Konstantin on 02.10.2017.
 */
public class ModelDTO extends EntityDTO {

    private String name;
    private Long company;

    public ModelDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCompany() {
        return company;
    }

    public void setCompany(Long company) {
        this.company = company;
    }
}
