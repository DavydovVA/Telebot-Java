package com.database;


import com.model.City;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class MongoDBPoperations {

    public boolean saveCity(MongoOperations mongoOp, City city) {
        Query searchCity = new Query(Criteria.where("cityName").is(city.getCityName()));
        City resultCity = mongoOp.findOne(searchCity, City.class);

        if (resultCity == null) {
            mongoOp.save(city);
            return true;
        }

        return false;
    }

    public City searchCity(MongoOperations mongoOp, String criteria, String value) {
        value = value.toUpperCase().charAt(0) + value.substring(1);

        Query searchCity = new Query(Criteria.where(criteria).is(value));

        return mongoOp.findOne(searchCity, City.class, "cities");
    }

    public void updateCity(MongoOperations mongoOp, String criteria, String value, String uCriteria, String uValue) {
        Query searchCity = new Query(Criteria.where(criteria).is(value));

        mongoOp.updateFirst(searchCity, Update.update(uCriteria, uValue), City.class, "cities");
    }

    public List<City> getAllCities(MongoOperations mongoOp) {
        return mongoOp.findAll(City.class, "cities");
    }

    public void removeCity(MongoOperations mongoOp, String criteria, String value) {
        Query searchCity = new Query(Criteria.where(criteria).is(value));

        mongoOp.remove(searchCity, City.class, "cities");
    }
}

