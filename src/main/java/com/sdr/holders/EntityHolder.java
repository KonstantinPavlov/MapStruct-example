package com.sdr.holders;

import com.sdr.entity.CarType;
import com.sdr.entity.Company;
import com.sdr.entity.Gender;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Konstantin on 29.09.2017.
 */
@Component
public class EntityHolder {
    private static final Logger LOGGER = Logger.getLogger(EntityHolder.class);

    private Map<String, Gender> genderMap = new HashMap<>();
    private Map<Long, CarType> carTypeMap = new HashMap<>();
    private Map<Long, Company> companyMap = new HashMap<>();

    public Gender getGender(String gender) {
        Gender gen = genderMap.get(gender);
        if (gender == null)
            LOGGER.error("Invalid gender: " + gender);
        return gen;
    }

    public CarType getCarType(Long id){
        return carTypeMap.get(id);
    }

    public Company getCompany(Long id){
        return companyMap.get(id);
    }

    @PostConstruct
    private void postConstruct() {
        LOGGER.info("Start PostConstruct.");

        LOGGER.info("Initializing gender Map.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Gender male = new Gender("MALE", 0L);
        Gender female = new Gender("FEMALE", 1L);

        genderMap.put(male.getName(), male);
        genderMap.put(female.getName(), female);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("Initializing Company Map.");
        Company opel = new Company("Opel", 0L);
        Company fiat = new Company("Fiat", 1L);
        companyMap.put(opel.getId(), opel);
        companyMap.put(fiat.getId(), fiat);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("Initializing carType Map.");
        carTypeMap.put(0L, new CarType("Sedan Opel", 0L));
        carTypeMap.put(1L, new CarType("Sedan Fiat", 1L));


        LOGGER.info("End PostConstruct.");
    }

}
