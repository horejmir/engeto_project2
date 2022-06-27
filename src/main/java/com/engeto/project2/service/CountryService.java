package com.engeto.project2.service;

import com.engeto.project2.dataDownload.Downloader;
import com.engeto.project2.entity.Country;
import com.engeto.project2.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;

        Downloader downloader =  new Downloader();
        this.countryRepository.saveAll(downloader.getCountryList());
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
