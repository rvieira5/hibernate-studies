package com.andcelsode.service;

import com.andcelsode.model.City;
import com.andcelsode.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public List<City> findByCity(String cityName) {
        return cityRepository.findByCityContainingIgnoreCase(cityName);
    }
}
