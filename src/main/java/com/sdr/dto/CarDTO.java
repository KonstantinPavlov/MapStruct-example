package com.sdr.dto;

import com.sdr.dto.base.EntityDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konstantin on 29.09.2017.
 */
public class CarDTO extends EntityDTO {

    private String name;
    private Long weight;

    private CarTypeDTO carType;
    private ModelDTO model;

    private List<PersonDTO> owners = new ArrayList<>();

    public CarDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelDTO getModel() {
        return model;
    }

    public void setModel(ModelDTO model) {
        this.model = model;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public List<PersonDTO> getOwners() {
        return owners;
    }

    public void setOwners(List<PersonDTO> owners) {
        this.owners = owners;
    }

    public CarTypeDTO getCarType() {
        return carType;
    }

    public void setCarType(CarTypeDTO carType) {
        this.carType = carType;
    }
}
