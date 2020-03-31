package com.database;

import com.database.config.MongoConfig;
import com.model.City;
import com.telebot.Commands;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

public class Peripheral {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    private MongoOperations mongoOp = (MongoOperations) ctx.getBean("mongoTemplate");
    private MongoDBPoperations ops = new MongoDBPoperations();

    private Commands c = new Commands();

    public StringBuilder showAll() {
        return ops.getAllCities(mongoOp);
    }

    public String show(String cityName) {
        City kek = ops.searchCity(mongoOp, "cityName", cityName);

        if (kek != null) {
            return kek.getCityName() + ": " + kek.getDescription().concat("\n");
        }

        return "No info about " + cityName + "\n";
    }

    public String add(String cityName, String description) {
        City kek = ops.searchCity(mongoOp, "cityName", cityName);

        if (kek == null) {
            City city = new City(cityName, description);
            ops.saveCity(mongoOp, city);

            return "Added.\n";
        }
        return "Already exists.\n";
    }

    public String update(String cityName, String description) {
        City kek = ops.searchCity(mongoOp, "cityName", cityName);

        if (kek != null) {
            ops.updateCity(mongoOp, "cityName", cityName, "description", description);
            return "Updated.\n";
        }

        return "Nothing to update.\n";
    }

    public String remove(String cityName) {
        City kek = ops.searchCity(mongoOp, "cityName", cityName);

        if (kek != null) {
            ops.removeCity(mongoOp, "cityName", cityName);
            return "Removed.\n";
        }
        return "Nothing to remove.\n";
    }

    public String getCommandDescription(String message) {
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
