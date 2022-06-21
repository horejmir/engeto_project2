package com.engeto.project2.repository;

import com.engeto.project2.entity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    @Query("select c from Country c where c.shortcutISO = ?1 or c.shortcutEU = ?1")
    Optional<Country> findByShortcut(String shortcut);

}