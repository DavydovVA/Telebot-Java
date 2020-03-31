package com.database;

import com.database.config.MongoConfig;
import com.model.City;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

public class Peripheral {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
    private MongoOperations mongoOp = (MongoOperations) ctx.getBean("mongoTemplate");
    private MongoDBPoperations ops = new MongoDBPoperations();


    public List<City> getAll() {
        return ops.getAllCities(mongoOp);
    }

    public City show(String cityName) {
        City kek = ops.searchCity(mongoOp, "cityName", cityName);

        if (kek != null) {
            return kek;
        }

        return new City(null, null);
    }

    public boolean add(String cityName, String description) {
        City kek = ops.searchCity(mongoOp, "cityName", cityName);

        if (kek == null) {
            City city = new City(cityName, description);
            ops.saveCity(mongoOp, city);

            return true;
        }
        return false;
    }

    public boolean update(String cityName, String description) {
        City kek = ops.searchCity(mongoOp, "cityName", cityName);

        if (kek != null) {
            ops.updateCity(mongoOp, "cityName", cityName, "description", description);
            return true;
        }

        return false;
    }

    public boolean remove(String cityName) {
        City kek = ops.searchCity(mongoOp, "cityName", cityName);

        if (kek != null) {
            ops.removeCity(mongoOp, "cityName", cityName);
            return true;
        }
        return false;
    }

}
