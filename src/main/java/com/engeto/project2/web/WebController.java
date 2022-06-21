package com.engeto.project2.web;

import com.engeto.project2.entity.Country;
import com.engeto.project2.service.RatesService;
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
    RatesService ratesService;

    @GetMapping("/")
    public String ratesTable(@RequestParam(required = false) String sort, Model model){

        Function<Country, Comparable> sortByCountryAttribute = Country::getId;

        if (sort != null) {
                switch (sort) {
                    case "name":
                        sortByCountryAttribute = Country::getName;
                        break;
                    case "shortcutISO":
                        sortByCountryAttribute = Country::getShortcutISO;
                        break;
                    case "standardRate":
                        sortByCountryAttribute = Country::getStandardRate;
                        break;
                    case "reducedRate":
                        sortByCountryAttribute = Country::getReducedRate;
                        break;
                    case "reducedRateAlt":
                        sortByCountryAttribute = Country::getReducedRateAlt;
                        break;
                    case "superReducedRate":
                        sortByCountryAttribute = Country::getSuperReducedRate;
                        break;
                    case "parkingRate":
                        sortByCountryAttribute = Country::getParkingRate;
                        break;
                }
        }

        Comparator<Country> comparator = Comparator.comparing(sortByCountryAttribute, Comparator.nullsFirst(Comparator.naturalOrder()));

        List<Country> countryList = ratesService.getAllCountry()
                .stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        model.addAttribute("countryList", countryList);

        return "ratesTable";
    }

}
