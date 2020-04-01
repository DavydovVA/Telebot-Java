package com.davydov.database;

import com.davydov.model.City;
import com.davydov.model.DatabaseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Peripheral implements DatabaseInterface {

    @Autowired
    private CityRepository repository;

    public Peripheral() {

    }

    @Override
    public City get(String cityName) {
        return repository.findByCityName(cityName);
    }

    @Override
    public List<City> getAll() {
        return repository.findAll();
    }

    @Override
    public void update(String cityName, String description) {
        repository.updateCity(cityName, description);
    }

    @Override
    public void add(String cityName, String description) {
        repository.addCity(cityName, description);
    }

    @Override
    public void remove(String cityName) {
        repository.removeCity(cityName);
    }
}
