package com.davydov.database;

import com.davydov.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    @Query(value = "SELECT * FROM cities WHERE city_name = :cityName", nativeQuery = true)
    City findByCityName(@Param("cityName") String cityName);

    @Modifying
    @Query(value = "DELETE FROM cities WHERE city_name = :cityName", nativeQuery = true)
    @Transactional
    void removeCity(@Param("cityName") String cityName);

    @Modifying
    @Query(value = "UPDATE cities SET description = :description WHERE city_name = :cityName", nativeQuery = true)
    @Transactional
    void updateCity(@Param("cityName") String cityName, @Param("description") String description);

    @Modifying
    @Query(value = "INSERT INTO cities (city_name, description) VALUES (:cityName, :description)", nativeQuery = true)
    @Transactional
    void addCity(@Param("cityName") String cityName, @Param("description") String description);

}
