package com.database;


import com.database.model.City;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.util.List;

public class MongoDBPoperations {

    public void saveCity(MongoOperations mongoOp, City city) {
        Query searchCity = new Query(Criteria.where("cityName").is(city.getCityName()));
        City resultCity = mongoOp.findOne(searchCity, City.class);

        if (resultCity == null) {
            mongoOp.save(city);
            System.out.println("City " + city.getCityName() + " saved successfully.");
        }
        System.out.println("City " + city.getCityName() + " already exists.");
    }

    public City searchCity(MongoOperations mongoOp, String criteria, String value) {
        value = value.toUpperCase().charAt(0) + value.substring(1);

        Query searchCity = new Query(Criteria.where(criteria).is(value));

        City resultCity = mongoOp.findOne(searchCity, City.class);

        System.out.println("City details: " + resultCity);
        return resultCity;
    }

    public void updateCity(MongoOperations mongoOp, String criteria,String value, String uCriteria, String uValue) {
        Query searchCity = new Query(Criteria.where(criteria).is(value));

        mongoOp.updateFirst(searchCity, Update.update(uCriteria, uValue), City.class);
        System.out.println("City got updated successfully");
    }

    public StringBuilder getAllCities(MongoOperations mongoOp) {
        List<City> listCity = mongoOp.findAll(City.class);

        StringBuilder cities = new StringBuilder();
        for(City city:listCity) {
            cities.append(city).append("\n");
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
        System.out.println("City removed successfully!! ");
    }
}

