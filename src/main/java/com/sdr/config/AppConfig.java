package com.sdr.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * Spring Config
 * <p>
 * Created by Konstantin on 29.09.2017.
 */

@Configuration
@PropertySource(value = {"classpath:log4j.properties"})
@ComponentScan("com.sdr")
public class AppConfig {


}
