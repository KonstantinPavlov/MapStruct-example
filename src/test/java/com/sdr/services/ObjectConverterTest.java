package com.sdr.services;

import com.sdr.config.AppConfig;
import com.sdr.dto.CarDTO;
import com.sdr.entity.Car;
import com.sdr.entity.Model;
import com.sdr.entity.Person;
import com.sdr.holders.EntityHolder;
import com.sdr.mappers.CarMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ObjectConverterTest {

    @Autowired
    private ObjectConverter converter;

    @Autowired
    private EntityHolder entityHolder;

    @Autowired
    private CarMapper carMapper;

    @Test
    public void converterTest() {
        Model model = new Model("Sedan", entityHolder.getCompany(0L), 1L);
        Car car = new Car("Opel astra", model, 3L, entityHolder.getCarType(1L), 10L);
        Person person = new Person("Neo", "Anderson", 27, entityHolder.getGender("MALE"));
        car.getOwners().add(person);
        CarDTO carDTO = carMapper.carToDTO(car);

        Map result = converter.parseToCollectionReflection(carDTO, carDTO.getClass());

        Assert.assertNotNull(result.get("getCarType"));
        Assert.assertNotNull(result.get("getId"));
        Assert.assertNotNull(result.get("getModel"));
        Assert.assertNotNull(result.get("getName"));
        Assert.assertNotNull(result.get("getWeight"));
        Assert.assertNotNull(result.get("getOwners"));
    }

}
