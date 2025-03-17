package com.andcelsode.service;

import com.andcelsode.exception.ValidationException;
import com.andcelsode.model.Country;
import com.andcelsode.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country findById(Short id) {
        return countryRepository.findById(id).orElseThrow(() ->
                new ValidationException("Country not found with ID:" + id)
        );
    }
}
