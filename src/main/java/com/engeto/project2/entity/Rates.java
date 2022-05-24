package com.engeto.project2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

    private HashMap<String, CountryTaxEntity> rates;

    public HashMap<String, CountryTaxEntity> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, CountryTaxEntity> rates) {

        for (Map.Entry<String, CountryTaxEntity> set : rates.entrySet()) {
            set.getValue().setCountryShortcut(set.getKey());
        }

        this.rates = rates;
    }

    public List<CountryTaxEntity> getRateList() {
        return new ArrayList<>(rates.values());
    }

    @Override
    public String toString() {
        return "Rates{" +
                "rates=" + rates +
                '}';
    }
}
