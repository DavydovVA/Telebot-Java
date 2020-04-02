package com.davydov.model;

import java.util.List;

public interface DatabaseInterface {

    List<City> getAll();

    City get(String cityName);

    void add(String cityName, String description);

    void update(String cityName, String description);

    void remove(String cityName);
}
