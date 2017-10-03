package com.sdr.holders;


import com.sdr.config.AppConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * Created by Konstantin on 02.10.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class HolderTest {

    private static final Logger LOGGER = Logger.getLogger(HolderTest.class);

    @Autowired
    private EntityHolder entityHolder;

    // Initialization holder test
    @Test
    public void holderTest() {
        LOGGER.info("===HolderTest===");
        // carType
        Assert.notNull(entityHolder.getCarType(0L));
        Assert.notNull(entityHolder.getCarType(1L));
        Assert.isNull(entityHolder.getCarType(1001213L));
        //Company
        Assert.notNull(entityHolder.getCompany(0L));
        Assert.notNull(entityHolder.getCompany(1L));
        Assert.isNull(entityHolder.getCompany(11514235216L));
        // Gender
        Assert.notNull(entityHolder.getGender("MALE"));
        Assert.notNull(entityHolder.getGender("FEMALE"));
        Assert.isNull(entityHolder.getGender("ALIEN"));

    }
}
