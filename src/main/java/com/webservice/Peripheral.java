package com.webservice;

import com.database.MongoDBPoperations;
import com.database.config.MongoConfig;
import com.database.model.City;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

public class Peripheral {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    private MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
    private MongoDBPoperations ops = new MongoDBPoperations();

    public StringBuilder showAll() {
        return ops.getAllCities(mongoOperation);
    }

    public String show(String cityName) {
        City kek = ops.searchCity(mongoOperation, "cityName", cityName);

        if (kek != null) {
            return kek.getCityName() + ": " + kek.getDescription().concat("\n");
        }

        return "No info about " + cityName + "\n";
    }

    public String add(String cityName, String description) {
        City kek = ops.searchCity(mongoOperation, "cityName", cityName);

        if (kek == null) {
            City city = new City(cityName, description);
            ops.saveCity(mongoOperation, city);

            return "Added.\n";
        }
        return "Already exists.\n";
    }

    public String update(String cityName, String description) {
        City kek = ops.searchCity(mongoOperation, "cityName", cityName);

        if (kek != null) {
            ops.updateCity(mongoOperation, "cityName", cityName, "description", description);
            return "Updated.\n";
        }

        return "Nothing to update.\n";
    }

    public String remove(String cityName) {
        City kek = ops.searchCity(mongoOperation, "cityName", cityName);

        if (kek != null) {
            ops.removeCity(mongoOperation, "cityName", cityName);
            return "Removed.\n";
        }
        return "Nothing to remove.\n";
    }


}
