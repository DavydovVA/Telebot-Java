package com.database;


import com.database.model.City;
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

        return mongoOp.findOne(searchCity, City.class);
    }

    public void updateCity(MongoOperations mongoOp, String criteria,String value, String uCriteria, String uValue) {
        Query searchCity = new Query(Criteria.where(criteria).is(value));

        mongoOp.updateFirst(searchCity, Update.update(uCriteria, uValue), City.class);
    }

    public StringBuilder getAllCities(MongoOperations mongoOp) {
        List<City> listCity = mongoOp.findAll(City.class);


        StringBuilder cities = new StringBuilder();
        for(City city:listCity) {
            cities.append(city.getCityName()).append("\n");
        }

        return cities;
    }

    public String getCitiesList(MongoOperations mongoOp) {
        List<City> listCity = mongoOp.findAll(City.class);

        StringBuilder cities = new StringBuilder();
        for(City city:listCity) {
            cities.append(city.getCityName()).append("\n");
        }

        return cities.toString();

    }

    public void removeCity(MongoOperations mongoOp, String criteria,String value) {
        Query searchCity = new Query(Criteria.where(criteria).is(value));

        mongoOp.remove(searchCity, City.class);
    }
}

