package com.engeto.project2.entity;

import com.engeto.project2.dataImportSupport.CountryRatesDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "country_rates")
@JsonDeserialize(using = CountryRatesDeserializer.class)
public class CountryRates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String shortcutISO;

    @Column
    private String shortcutEU;

    @Column
    private String countryName;

    @Column
    private Double standardRate;

    @Column
    private Double reducedRate;

    @Column
    private Double reducedRateAlt;

    @Column
    private Double superReducedRate;

    @Column
    private Double parkingRate;

    @Override
    public String toString() {
        return "CountryRates{" +
                "id=" + id +
                ", shortcutISO='" + shortcutISO + '\'' +
                ", shortcutEU='" + shortcutEU + '\'' +
                ", countryName='" + countryName + '\'' +
                ", standardRate=" + standardRate +
                ", reducedRate=" + reducedRate +
                ", reducedRateAlt=" + reducedRateAlt +
                ", superReducedRate=" + superReducedRate +
                ", parkingRate=" + parkingRate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryRates that = (CountryRates) o;
        return shortcutISO.equals(that.shortcutISO) && shortcutEU.equals(that.shortcutEU) && countryName.equals(that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName);
    }


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getShortcutISO() {
        return shortcutISO;
    }

    public void setShortcutISO(String shortcutISO) {
        this.shortcutISO = shortcutISO;
    }

    public String getShortcutEU() {
        return shortcutEU;
    }

    public void setShortcutEU(String shortcutEU) {
        this.shortcutEU = shortcutEU;
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

    public Double getReducedRateAlt() {
        return reducedRateAlt;
    }

    public void setReducedRateAlt(Double reducedRateAlt) {
        this.reducedRateAlt = reducedRateAlt;
    }

    public Double getSuperReducedRate() {
        return superReducedRate;
    }

    public void setSuperReducedRate(Double superReducedRate) {
        this.superReducedRate = superReducedRate;
    }

    public Double getParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(Double parkingRate) {
        this.parkingRate = parkingRate;
    }
}
