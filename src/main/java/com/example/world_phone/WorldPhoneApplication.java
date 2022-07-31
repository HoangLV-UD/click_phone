package com.example.world_phone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WorldPhoneApplication {
    public static void main(String[] args) {
        SpringApplication.run(WorldPhoneApplication.class, args);
    }

}
