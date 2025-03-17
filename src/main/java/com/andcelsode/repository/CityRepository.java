package com.andcelsode.repository;

import com.andcelsode.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Short> {
    List<City> findByCityContainingIgnoreCase(String cityName);
}
