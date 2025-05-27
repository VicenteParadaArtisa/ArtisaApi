package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

    @Value("${spring.data.mongodb.uri:NO_URI_FOUND}")
    private String mongoDbUri;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            System.out.println("----------------------------------------------------");
            System.out.println("MongoDB URI utilizada: " + mongoDbUri);
            System.out.println("----------------------------------------------------");
        };
    }
}
