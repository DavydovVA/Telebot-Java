package com.database.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import com.mongodb.MongoClient;
import javax.annotation.Nonnull;



@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    @Nonnull
    public String getDatabaseName() {
        return "test";
    }

    @Override
    @Nonnull
    @Bean
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1");
    }
}
