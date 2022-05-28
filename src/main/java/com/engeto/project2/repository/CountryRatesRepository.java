package com.engeto.project2.repository;

import com.engeto.project2.entity.CountryRates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRatesRepository extends CrudRepository<CountryRates, Long> {

}