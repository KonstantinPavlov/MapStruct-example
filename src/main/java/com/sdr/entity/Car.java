package com.sdr.entity;

import com.sdr.entity.base.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konstantin on 29.09.2017.
 */
public class Car extends Entity {

    private String name;

    private Long weight;

    private CarType carType;

    private Model model;

    private List<Person> owners = new ArrayList<>();

    public Car() {
    }

    public Car(String name, Model model, Long weight, CarType carType, Long id) {
        this.name = name;
        this.model = model;
        this.weight = weight;
        this.carType = carType;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public List<Person> getOwners() {
        return owners;
    }

    public void setOwners(List<Person> owners) {
        this.owners = owners;
    }
}
