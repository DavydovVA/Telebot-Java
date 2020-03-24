package com.database.model;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cities")
public class City {

    private String cityName;

    private String description;


    public City(String cityName, String description) {
        this.cityName = cityName;
        this.description = description;
    }

    public String getCityName() {
        return this.cityName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
          "City: %s, Description: %s",
                this.cityName, this.description);
    }
}
