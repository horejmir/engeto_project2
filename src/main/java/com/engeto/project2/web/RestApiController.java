package com.engeto.project2.web;

import com.engeto.project2.entity.Country;
import com.engeto.project2.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class RestApiController {

    @Autowired
    CountryService countryService;

    @GetMapping("/api/rates")
    public Iterable<Country> getAllCountry(){
        return countryService.getAllCountry();
    }

    @GetMapping("/api/rates/shortcut/{shortcut}")
    public Optional<Country> getCountryByShortcut(@PathVariable String shortcut) {
        return countryService.getCountryByShortcut(shortcut.toUpperCase());
    }

    @GetMapping("/api/rates/{id}")
    public Optional<Country> getCountryById(@PathVariable Long id) {
        return countryService.getCountryById(id);
    }

    @PostMapping("/api/rates/")
    public Country saveCountry(@RequestBody Country country) {

        countryService.saveCountry(country);

        return country;
    }

    @PutMapping("/api/rates/{id}")
    public Country updateCountry(@RequestBody Country country, @PathVariable Long id) {

        return countryService.saveCountry(country);
    }

    @DeleteMapping("/api/rates/{id}")
    public void deleteCountry(@PathVariable Long id){
        countryService.deleteCountryById(id);
    }

}
