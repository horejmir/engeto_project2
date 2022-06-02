package com.engeto.project2.web;

import com.engeto.project2.entity.CountryRates;
import com.engeto.project2.service.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestApiController {

    @Autowired
    RatesService ratesService;

    @GetMapping("/api/rates")
    public Iterable<CountryRates> getAllCountryRates(){
        return ratesService.getAllCountryRates();
    }

    @GetMapping("/api/rates/{shortcut}")
    public CountryRates geCountryRatesByShortcut(@PathVariable String shortcut) {
        return ratesService.geCountryRatesByShortcut(shortcut.toUpperCase());
    }

}
