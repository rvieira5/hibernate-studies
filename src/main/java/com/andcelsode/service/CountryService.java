package com.andcelsode.service;

import com.andcelsode.exception.ValidationException;
import com.andcelsode.model.City;
import com.andcelsode.model.Country;
import com.andcelsode.repository.CityRepository;
import com.andcelsode.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CityRepository cityRepository;

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country findById(Short id) {
        return countryRepository.findById(id).orElseThrow(() ->
                new ValidationException("Country not found with ID:" + id)
        );
    }

    public List<City> findCitiesByCountry(Short id, String countryName) {
        if (id != null) {
            return findCitiesByIdCountry(id);
        } else if (countryName != null) {
            return findCitiesByNameCountry(countryName);
        } else {
            throw new IllegalArgumentException("Either id or name must be provided");
        }
    }

    public List<City> findCitiesByIdCountry(Short id) {
        return cityRepository.findByCountryId(id);
    }

    public List<City> findCitiesByNameCountry(String nameCountry) {
        return cityRepository.findByCountryName(nameCountry);
    }
}
