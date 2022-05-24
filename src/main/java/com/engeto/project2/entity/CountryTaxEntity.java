package com.engeto.project2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = CountryTaxEntityDeserializer.class)
public class CountryTaxEntity {

    private String countryShortcut;

    //@JsonProperty("country")
    private String countryName;

    //@JsonProperty("standard_rate")
    private Double standardRate;

    //@JsonProperty("reduced_rate")
    private Double reducedRate;

    //@JsonProperty("reduced_rate_alt")
    private Double ReduceRateAlt;

    //@JsonProperty("super_reduced_rate")
    private Double superReduceRate;

    //@JsonProperty("parking_rate")
    private Double parkingRate;

    @Override
    public String toString() {
        return "CountryTaxEntity{" +
                "countryShortcut='" + countryShortcut + '\'' +
                ", countryName='" + countryName + '\'' +
                ", standardRate=" + standardRate +
                ", reducedRate=" + reducedRate +
                ", ReduceRateAlt=" + ReduceRateAlt +
                ", superReduceRate=" + superReduceRate +
                ", parkingRate=" + parkingRate +
                '}';
    }

    public String getCountryShortcut() {
        return countryShortcut;
    }

    public void setCountryShortcut(String countryShortcut) {
        this.countryShortcut = countryShortcut;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(Double standardRate) {
        this.standardRate = standardRate;
    }

    public Double getReducedRate() {
        return reducedRate;
    }

    public void setReducedRate(Double reducedRate) {
        this.reducedRate = reducedRate;
    }

    public Double getReduceRateAlt() {
        return ReduceRateAlt;
    }

    public void setReduceRateAlt(Double reduceRateAlt) {
        ReduceRateAlt = reduceRateAlt;
    }

    public Double getSuperReduceRate() {
        return superReduceRate;
    }

    public void setSuperReduceRate(Double superReduceRate) {
        this.superReduceRate = superReduceRate;
    }

    public Double getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(Double parkingRate) {
        this.parkingRate = parkingRate;
    }
}
