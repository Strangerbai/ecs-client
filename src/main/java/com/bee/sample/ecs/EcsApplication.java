package com.bee.sample.ecs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class EcsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcsApplication.class, args);
    }




}
