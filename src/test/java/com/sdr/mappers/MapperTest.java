package com.sdr.mappers;

import com.sdr.config.AppConfig;
import com.sdr.dto.CarDTO;
import com.sdr.dto.CarTypeDTO;
import com.sdr.dto.ModelDTO;
import com.sdr.dto.PersonDTO;
import com.sdr.entity.Car;
import com.sdr.entity.CarType;
import com.sdr.entity.Model;
import com.sdr.entity.Person;
import com.sdr.holders.EntityHolder;
import com.sdr.services.EnterPointTest;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Konstantin on 29.09.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class MapperTest {

    private static final Logger LOGGER = Logger.getLogger(MapperTest.class);

    @Autowired
    private EntityHolder entityHolder;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private CarTypeMapper carTypeMapper;

    @Test
    public void carTypeMapperTest() {
        LOGGER.info("===CarTypeMapperTest===");
        CarType carType = entityHolder.getCarType(0L);
        CarTypeDTO carTypeDTO = carTypeMapper.carTypeToDTO(carType);
        Assert.assertEquals(carType.getName(), carTypeDTO.getName());
        Assert.assertEquals(carType.getId(), carTypeDTO.getId());
    }

    @Test
    public void modelMapperTest() {
        LOGGER.info("===ModelMapperTest===");
        Model model = new Model("Sednba", entityHolder.getCompany(0L), 1L);
        ModelDTO modelDTO = modelMapper.modelToDTO(model);
        Assert.assertEquals(model.getName(), modelDTO.getName());
        Assert.assertEquals(model.getId(), modelDTO.getId());
        Assert.assertEquals(model.getCompany().getId(), modelDTO.getCompany());
    }

    @Test
    public void personMapperTest() {
        LOGGER.info("===PersonMapperTest===");
        Person person = new Person("Neo", "Anderson", 27, entityHolder.getGender("MALE"));
        PersonDTO personDTO = personMapper.personToDTO(person);
        Assert.assertEquals(person.getAge(), personDTO.getAge());
        Assert.assertEquals(person.getLastName(), personDTO.getLastName());
        Assert.assertEquals(person.getName(), personDTO.getName());
        Assert.assertEquals(person.getGender().getId(), personDTO.getGender());
    }

    @Test
    public void carMappingTest() {
        LOGGER.info("===CarMappingTest===");
        Model model = new Model("Sednba", entityHolder.getCompany(0L), 1L);
        Car car = new Car("Opel astra", model, 3L, entityHolder.getCarType(1L), 10L);
        Person person = new Person("Neo", "Anderson", 27, entityHolder.getGender("MALE"));
        car.getOwners().add(person);
        CarDTO carDTO = carMapper.carToDTO(car);

        Assert.assertEquals(car.getName(), carDTO.getName());
        Assert.assertEquals(car.getWeight(), carDTO.getWeight());
        Assert.assertEquals(car.getId(), carDTO.getId());
        //Model
        Assert.assertEquals(car.getModel().getCompany().getId(), carDTO.getModel().getCompany());
        Assert.assertEquals(car.getModel().getName(), carDTO.getModel().getName());
        Assert.assertEquals(car.getModel().getId(), carDTO.getModel().getId());
        // CarType
        Assert.assertEquals(carDTO.getCarType().getName(), entityHolder.getCarType(1L).getName());
        Assert.assertEquals(carDTO.getCarType().getId(), car.getCarType().getId());
        // Person
        Assert.assertFalse(carDTO.getOwners().isEmpty());
        Assert.assertEquals(car.getOwners().size(), carDTO.getOwners().size());
        Assert.assertEquals(car.getOwners().get(0).getName(), carDTO.getOwners().get(0).getName());
        Assert.assertEquals(car.getOwners().get(0).getGender().getId(), carDTO.getOwners().get(0).getGender());
    }

}
