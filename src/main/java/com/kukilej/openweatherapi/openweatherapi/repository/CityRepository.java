package com.kukilej.openweatherapi.openweatherapi.repository;

import com.kukilej.openweatherapi.openweatherapi.model.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
