package com.engeto.project2.dataDownload;

import com.engeto.project2.entity.CountryRates;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

    private List<CountryRates> ratesList;

    public void setRates(HashMap<String, CountryRates> rates) {

        for (Map.Entry<String, CountryRates> set : rates.entrySet()) {
            if (set.getValue().getShortcutEU() != null && !set.getValue().getShortcutEU().equals(set.getKey())) {
                set.getValue().setShortcutISO(set.getKey());
            } else if (set.getValue().getShortcutISO() != null && !set.getValue().getShortcutISO().equals(set.getKey())) {
                set.getValue().setShortcutEU(set.getKey());
            } else {
                set.getValue().setShortcutISO(set.getKey());
                set.getValue().setShortcutEU(set.getKey());
            }
        }

        List<CountryRates> countryRatesList = new ArrayList<>(rates.values());
        this.ratesList = countryRatesList.stream().distinct().collect(Collectors.toList());
    }

    public List<CountryRates> getRatesList() {
        return ratesList;
    }

}
