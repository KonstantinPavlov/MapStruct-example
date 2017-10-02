package com.sdr.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * Created by Konstantin on 29.09.2017.
 */

@Component
public class EnterPoint {

    private static final Logger LOGGER = Logger.getLogger(EnterPoint.class);

    private static final String MESSAGE = "Hello!";

    public EnterPoint() {
    }

    void hello() {
        System.out.println("Message from stdout : " + MESSAGE);
        LOGGER.info(MESSAGE);
    }
}
