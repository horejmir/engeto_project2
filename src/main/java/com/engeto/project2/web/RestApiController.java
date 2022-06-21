package com.engeto.project2.web;

import com.engeto.project2.entity.Country;
import com.engeto.project2.service.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class RestApiController {

    @Autowired
    RatesService ratesService;

    @GetMapping("/api/rates")
    public Iterable<Country> getAllCountry(){
        return ratesService.getAllCountry();
    }

    @GetMapping("/api/rates/shortcut/{shortcut}")
    public Optional<Country> getCountryByShortcut(@PathVariable String shortcut) {
        return ratesService.getCountryByShortcut(shortcut.toUpperCase());
    }

    @GetMapping("/api/rates/{id}")
    public Optional<Country> getCountryById(@PathVariable Long id) {
        return ratesService.getCountryById(id);
    }

    @PostMapping("/api/rates/")
    public Country saveCountry(@RequestBody Country country) {

        ratesService.saveCountry(country);

        return country;
    }

    @PutMapping("/api/rates/{id}")
    public Country updateCountry(@RequestBody Country country, @PathVariable Long id) {

        return ratesService.saveCountry(country);
    }

    @DeleteMapping("/api/rates/{id}")
    public void deleteCountry(@PathVariable Long id){
        ratesService.deleteCountryById(id);
    }

}
