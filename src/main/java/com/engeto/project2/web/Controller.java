package com.engeto.project2.web;

import com.engeto.project2.entity.CountryRates;
import com.engeto.project2.service.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    RatesService ratesService;

    @GetMapping("/")
    public String ratesTable(){

        for(CountryRates cr : ratesService.getAllCountryRates())
            System.out.println(cr);

        return "hello";
    }

}
