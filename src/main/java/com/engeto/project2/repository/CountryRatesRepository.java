package com.engeto.project2.repository;

import com.engeto.project2.entity.CountryRates;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRatesRepository extends CrudRepository<CountryRates, Long> {

    @Query("select c from CountryRates c where c.shortcutISO = ?1 or c.shortcutEU = ?1")
    CountryRates findByShortcut(String shortcut);

}