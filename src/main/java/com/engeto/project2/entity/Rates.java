package com.engeto.project2.entity;

import java.util.HashMap;

public class Rates {

    private HashMap<String, CountryTaxEntity> rates;

    public HashMap<String, CountryTaxEntity> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, CountryTaxEntity> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Rates{" +
                "rates=" + rates +
                '}';
    }
}
