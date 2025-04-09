package com.example.seance2eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Seance2eurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Seance2eurekaApplication.class, args);
    }

}
