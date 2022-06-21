package com.engeto.project2.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String shortcutISO;

    @Column
    private String shortcutEU;

    @Column
    private String name;

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
        return "Country{" +
                "id=" + id +
                ", shortcutISO='" + shortcutISO + '\'' +
                ", shortcutEU='" + shortcutEU + '\'' +
                ", name='" + name + '\'' +
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
        Country that = (Country) o;
        return shortcutISO.equals(that.shortcutISO) && shortcutEU.equals(that.shortcutEU) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
