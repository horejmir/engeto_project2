package com.engeto.project2.dataImport;

import com.engeto.project2.entity.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RatesImport {

    private final String DATA_DOWNLOAD_ADDRESS = "https://euvatrates.com/rates.json";

    public List<Country> getCountryList() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Country.class, new CountryImportDeserializer());
        objectMapper.registerModule(module);

        Rates rates = null;

        try {
            rates = objectMapper.readValue(new URL(DATA_DOWNLOAD_ADDRESS), Rates.class);
            System.out.println("VAT RATES SUCCESSFULLY DOWNLOADED FROM: " + DATA_DOWNLOAD_ADDRESS + ", ON: "
                    + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            System.out.println("\t- LAST UPDATED: " + rates.getLastUpdated());
            System.out.println("\t- DISCLAIMER: " + rates.getDisclaimer());
        } catch (IOException e) {
            System.err.println("UNABLE TO DOWNLOAD VAT RATES FROM: " + DATA_DOWNLOAD_ADDRESS);
        }

        if (rates == null) {
            return null;
        } else {
            return rates.getCountryList();
        }

    }
}
