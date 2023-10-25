package com.esprit.microservices.candidate;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class Atelier1Application {

    public static void main(String[] args) {
        SpringApplication.run(Atelier1Application.class, args);
    }


    @Bean
    ApplicationRunner init() {
        return (args) -> {
// save

        };
    }
}
