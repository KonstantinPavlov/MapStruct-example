package com.sdr.entity.base;

/**
 * Created by Konstantin on 29.09.2017.
 */
public abstract class Entity {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
