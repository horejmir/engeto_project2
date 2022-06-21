package com.engeto.project2.service;

import com.engeto.project2.dataImport.RatesImport;
import com.engeto.project2.entity.Country;
import com.engeto.project2.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RatesService {

    private final CountryRepository countryRepository;

    public RatesService(CountryRepository countryRepository ) {
        this.countryRepository = countryRepository;

        RatesImport ratesImport = new RatesImport();
        List<Country> countryList = ratesImport.getCountryList();

        if (countryList != null) {
            countryRepository.saveAll(countryList);
        }
    }

    public List<Country> getAllCountry() {
        return StreamSupport
                .stream(countryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Country> getCountryByShortcut(String shortcut) {
        return countryRepository.findByShortcut(shortcut);
    }

    public Optional<Country> getCountryById(Long id){
        return countryRepository.findById(id);
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    public void deleteCountryById(Long id) {
        countryRepository.deleteById(id);
    }
}
