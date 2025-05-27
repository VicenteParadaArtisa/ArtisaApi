package com.example.apijsonsearch.config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(MongoClients.create(
            "mongodb+srv://admin:AdminArtisa2025@jsonrepoartisa.83kjdw0.mongodb.net/dplores?retryWrites=true&w=majority&appName=JsonRepoArtisa"
        ), "dplores");
    }
}
