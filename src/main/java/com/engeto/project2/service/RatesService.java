package com.engeto.project2.service;

import com.engeto.project2.entity.CountryRates;
import com.engeto.project2.dataDownload.RatesDownload;
import com.engeto.project2.repository.CountryRatesRepository;
import org.springframework.stereotype.Service;

@Service
public class RatesService {

    private final CountryRatesRepository countryRatesRepository;

    public RatesService(CountryRatesRepository countryRatesRepository ) {
        this.countryRatesRepository = countryRatesRepository;
        RatesDownload ratesDownload = new RatesDownload();

        countryRatesRepository.saveAll(ratesDownload.get());
    }

    public Iterable<CountryRates> getAllCountryRates() {
        return countryRatesRepository.findAll();
    }
}
