package com.dayasolusi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BackendDayaSolusiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BackendDayaSolusiApplication.class, args);
    }


    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
        return springApplicationBuilder.sources(BackendDayaSolusiApplication.class);
    }
}
