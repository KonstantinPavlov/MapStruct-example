package com.sdr.entity;

import com.sdr.entity.base.Entity;

/**
 * Created by Konstantin on 29.09.2017.
 */
public class CarType extends Entity {

    private String name;

    public CarType() {
    }

    public CarType(String name, Long id) {
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
