package com.engeto.project2.web;

import com.engeto.project2.entity.Country;
import com.engeto.project2.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class WebController {

    @Autowired
    CountryService countryService;

    @GetMapping("/")
    public String ratesTable(@RequestParam(required = false) String sort, Model model){

        Function<Country, Comparable> sortByCountryAttribute = Country::getId;

        if (sort != null) {
            switch (sort) {
                case "name" -> sortByCountryAttribute = Country::getName;
                case "shortcutISO" -> sortByCountryAttribute = Country::getShortcutISO;
                case "standardRate" -> sortByCountryAttribute = Country::getStandardRate;
                case "reducedRate" -> sortByCountryAttribute = Country::getReducedRate;
                case "reducedRateAlt" -> sortByCountryAttribute = Country::getReducedRateAlt;
                case "superReducedRate" -> sortByCountryAttribute = Country::getSuperReducedRate;
                case "parkingRate" -> sortByCountryAttribute = Country::getParkingRate;
            }
        }

        Comparator<Country> comparator = Comparator
                .comparing(sortByCountryAttribute, Comparator.nullsFirst(Comparator.naturalOrder()));

        List<Country> countryList = countryService.getAllCountry()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        model.addAttribute("countryList", countryList);

        return "countryTable";
    }

}
