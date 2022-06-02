package com.engeto.project2.dataImportSupport;

import com.engeto.project2.entity.CountryRates;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

    private String disclaimer;

    private HashMap<String, CountryRates> ratesMap;

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setRates(HashMap<String, CountryRates> ratesMap) {

        for (Map.Entry<String, CountryRates> set : ratesMap.entrySet()) {
            if (set.getValue().getShortcutEU() != null && !set.getValue().getShortcutEU().equals(set.getKey())) {
                set.getValue().setShortcutISO(set.getKey());
            } else if (set.getValue().getShortcutISO() != null && !set.getValue().getShortcutISO().equals(set.getKey())) {
                set.getValue().setShortcutEU(set.getKey());
            } else {
                set.getValue().setShortcutISO(set.getKey());
                set.getValue().setShortcutEU(set.getKey());
            }
        }

        this.ratesMap = ratesMap;
    }

    public List<CountryRates> getRatesList() {

        return new ArrayList<>(ratesMap.values())
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

}
