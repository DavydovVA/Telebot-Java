package com.database;

import com.database.MongoDBPoperations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import com.database.config.MongoConfig;
import com.database.model.City;


public class Cities_DB {
    // TEST CLASS
    public static void main (String[] args) {
        // For Annotation
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        MongoDBPoperations ops = new MongoDBPoperations();

        City city = new City("Minsk", "Nice city.");
        City city2 = new City("Moscow", "Nice city.");

        //save student
        ops.saveCity(mongoOperation, city);
        ops.saveCity(mongoOperation, city2);

        // get student based on search criteria
        City kek = ops.searchCity(mongoOperation, "cityName", "Minsk");
        System.out.println(kek.getCityName());
        //update student based on criteria
        ops.updateCity(mongoOperation, "cityName", "Minsk", "description", "Meme Keke");
        // get student based on search criteria
        ops.searchCity(mongoOperation, "cityName", "Minsk");

        // get all the students
        //ops.getAllCities(mongoOperation);

        //remove student based on criteria
        //ops.removeCity(mongoOperation, "cityName", "Moscow");
        // get all the students
        ops.getAllCities(mongoOperation);


    }
}
