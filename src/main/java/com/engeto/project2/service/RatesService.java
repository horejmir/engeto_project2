package com.engeto.project2.service;

import com.engeto.project2.dataImportSupport.Rates;
import com.engeto.project2.entity.CountryRates;
import com.engeto.project2.repository.CountryRatesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

@Service
public class RatesService {

    private final CountryRatesRepository countryRatesRepository;

    public RatesService(CountryRatesRepository countryRatesRepository ) {
        this.countryRatesRepository = countryRatesRepository;

        String ratesUrl = "https://euvatrates.com/rates.json";

        ObjectMapper objectMapper = new ObjectMapper();
        Rates rates = null;

        try {
            rates = objectMapper.readValue(new URL(ratesUrl), Rates.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (rates != null) {
            List<CountryRates> ratesList = rates.getRatesList();
            ratesList.sort(Comparator.comparing(CountryRates::getCountryName));
            countryRatesRepository.saveAll(ratesList);
        }
    }

    public Iterable<CountryRates> getAllCountryRates() {

        return countryRatesRepository.findAll();
    }
}
