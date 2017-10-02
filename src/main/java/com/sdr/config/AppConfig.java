package com.sdr.config;

import com.sdr.services.EnterPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


/**
 * Created by Konstantin on 29.09.2017.
 */

@Configuration
@PropertySource(value = {"classpath:log4j.properties"})
@ComponentScan("com.sdr")
public class AppConfig {


}
