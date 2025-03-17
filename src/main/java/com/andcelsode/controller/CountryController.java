package com.andcelsode.controller;

import com.andcelsode.model.City;
import com.andcelsode.model.Country;
import com.andcelsode.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public Country findById(@PathVariable("id") Short id) {
        return countryService.findById(id);
    }

    @GetMapping("/cities")
    public List<City> findCitiesByCountry(
            @RequestParam(value = "id", required = false) Short id,
            @RequestParam(value = "name", required = false) String countryName

    ) {
        return countryService.findCitiesByCountry(id, countryName);
    }
}
