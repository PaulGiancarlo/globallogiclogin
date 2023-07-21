package com.loginusers.pdiaz.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {

    private String secret;
    private Long expiration;
}
