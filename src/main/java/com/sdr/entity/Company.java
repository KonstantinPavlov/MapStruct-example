package com.sdr.entity;

import com.sdr.entity.base.Entity;

/**
 * Created by Konstantin on 29.09.2017.
 */
public class Company extends Entity {

    private String name;

    public Company() {
    }

    public Company(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
