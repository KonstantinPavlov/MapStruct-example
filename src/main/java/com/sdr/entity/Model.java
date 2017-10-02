package com.sdr.entity;

import com.sdr.entity.base.Entity;

/**
 * Created by Konstantin on 02.10.2017.
 */
public class Model extends Entity {

    private String name;
    private Company company;

    public Model() {
    }

    public Model(String name, Company company, Long id) {
        this.name = name;
        this.company = company;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
