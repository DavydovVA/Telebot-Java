package com.telebot;

import com.database.MongoDBPoperations;
import com.database.config.MongoConfig;
import com.database.model.City;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

public class Peripheral {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    private MongoOperations mongoOp = (MongoOperations) ctx.getBean("mongoTemplate");
    private MongoDBPoperations ops = new MongoDBPoperations();

    private Commands c = new Commands();


    public String getCommandDescription(String message){
        return c.getDescription(message, ops, mongoOp);
    }

    public String getCityDescription(String message) {
        City city = ops.searchCity(mongoOp, "cityName", message);

        if (city != null) {
            return city.getDescription();
        }
        return null;
    }

}
