package com.engeto.project2.dataDownload;

import com.engeto.project2.entity.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

    @JsonProperty("last_updated")
    private LocalDate lastUpdated;

    private String disclaimer;

    @JsonProperty("rates")
    private HashMap<String, Country> ratesMap;

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public HashMap<String, Country> getRatesMap() {
        return ratesMap;
    }

    public void setRatesMap(HashMap<String, Country> ratesMap) {

        for (Map.Entry<String, Country> set : ratesMap.entrySet()) {
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
}
