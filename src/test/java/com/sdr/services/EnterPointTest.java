package com.sdr.services;

import com.sdr.config.AppConfig;
import com.sdr.holders.EntityHolder;
import org.apache.log4j.Logger;
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
public class EnterPointTest {

    private static final Logger LOGGER = Logger.getLogger(EnterPointTest.class);

    @Autowired
    private EnterPoint enterPoint;

    @Test
    public void testConfig() {
        LOGGER.info("===EnterPointTest===");
        enterPoint.hello();
    }
}
