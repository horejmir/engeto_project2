package com.engeto.project2.dataDownload;

import com.engeto.project2.entity.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Downloader {

    private final String DOWNLOAD_ADDRESS;

    public Downloader() {

        DOWNLOAD_ADDRESS = "https://euvatrates.com/rates.json";
    }

    public Downloader(String downloadAddress) {

        DOWNLOAD_ADDRESS = downloadAddress;
    }

    public List<Country> getCountryList() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Country.class, new CountryDeserializer());
        objectMapper.registerModule(module);

        try {
            Rates rates = objectMapper.readValue(new URL(DOWNLOAD_ADDRESS), Rates.class);
            System.out.println("VAT RATES SUCCESSFULLY DOWNLOADED FROM: " + DOWNLOAD_ADDRESS + ", ON: "
                    + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            System.out.println("\t- LAST UPDATED: " + rates.getLastUpdated());
            System.out.println("\t- DISCLAIMER: " + rates.getDisclaimer());

            return new ArrayList<>(rates.getRatesMap().values())
                        .stream()
                        .distinct()
                        .collect(Collectors.toList());

        } catch (IOException e) {
            System.err.println("UNABLE TO DOWNLOAD VAT RATES FROM: " + DOWNLOAD_ADDRESS);
        }

        return new ArrayList<>();
    }
}