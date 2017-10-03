package com.sdr.services;


import com.sdr.config.AppConfig;
import com.sdr.dto.CarDTO;
import com.sdr.entity.Car;
import com.sdr.entity.Model;
import com.sdr.entity.Person;
import com.sdr.holders.EntityHolder;
import com.sdr.mappers.CarMapper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class ObjectDifferTest {

    private static final Logger LOGGER = Logger.getLogger(ObjectDifferTest.class);

    @Autowired
    private ObjectConverter converter;

    @Autowired
    private EntityHolder entityHolder;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ObjectDiffer differ;

    @Test
    public void differTest() {

        Model model = new Model("Sedan", entityHolder.getCompany(0L), 1L);
        Car car = new Car("Opel astra", model, 3L, entityHolder.getCarType(1L), 10L);
        Person person = new Person("Neo", "Anderson", 27, entityHolder.getGender("MALE"));
        car.getOwners().add(person);
        CarDTO carDTO = carMapper.carToDTO(car);

        // Не измененный объект
        LOGGER.info("No changes for object");
        Assert.assertFalse(differ.isDifferent(carDTO,car));

        // Изменение простого поля
        car.setName("Opel ne astra");
        LOGGER.info("Changes in name");
        Assert.assertTrue(differ.isDifferent(carDTO,car));

        // Тест на ссылку
        LOGGER.info("Changes in link - model");
        Model model_two = new Model("Sedan 2", entityHolder.getCompany(1L), 2L);
        car.setModel(model_two);
        car.setName("Opel astra");
        car.getCarType().setName("New name");
        Assert.assertTrue(differ.isDifferent(carDTO,car));

    }
}
