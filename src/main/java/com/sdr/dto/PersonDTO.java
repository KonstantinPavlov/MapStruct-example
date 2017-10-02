package com.sdr.dto;

import com.sdr.dto.base.EntityDTO;


/**
 * Created by Konstantin on 29.09.2017.
 */
public class PersonDTO extends EntityDTO {

    private String name;
    private String lastName;
    private int age;

    private Long gender;

    public PersonDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }
}
