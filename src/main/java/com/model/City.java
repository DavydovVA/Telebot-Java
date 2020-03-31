package com.model;

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
        return this.cityName;
    }
}
