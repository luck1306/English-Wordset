package com.example.englishwordset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class EnglishWordsetApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnglishWordsetApplication.class, args);
    }

}
