package com.engeto.project2.entity;

import com.google.gson.annotations.SerializedName;

public class CountryTaxEntity {


    private String countryShortcut;

    @SerializedName(value = "country")
    private String countryName;

    @SerializedName(value = "standard_rate")
    private Double standardRate;

    @SerializedName(value = "reduced_rate")
    private String reducedRate;

    @SerializedName(value = "reduced_rate_alt")
    private String ReduceRateAlt;

    @SerializedName(value = "super_reduced_rate")
    private String superReduceRate;

    @SerializedName(value = "parking_rate")
    private String parkingRate;


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

    public String getReducedRate() {
        return reducedRate;
    }

    public void setReducedRate(String reducedRate) {
        this.reducedRate = reducedRate;
    }

    public String getReduceRateAlt() {
        return ReduceRateAlt;
    }

    public void setReduceRateAlt(String reduceRateAlt) {
        ReduceRateAlt = reduceRateAlt;
    }

    public String getSuperReduceRate() {
        return superReduceRate;
    }

    public void setSuperReduceRate(String superReduceRate) {
        this.superReduceRate = superReduceRate;
    }

    public String getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(String parkingRate) {
        this.parkingRate = parkingRate;
    }
}
