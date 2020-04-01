package com.davydov.model;


import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {

    private long id;
    private String cityName;
    private String description;

    public City() {

    }

    public City(String cityName, String description) {
        this.cityName = cityName;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "city_name", nullable = false)
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("City :[id=%s , city_name=%s, description=%s]", this.id, this.cityName, this.description);
    }
}
