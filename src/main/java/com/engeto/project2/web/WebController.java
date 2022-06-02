package com.engeto.project2.web;

import com.engeto.project2.entity.CountryRates;
import com.engeto.project2.service.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class WebController {

    @Autowired
    RatesService ratesService;

    @GetMapping("/")
    public String ratesTable(HttpServletRequest request, Model model){

        List<CountryRates> ratesList = StreamSupport
                .stream(ratesService.getAllCountryRates().spliterator(), false)
                .sorted(Comparator.comparing(CountryRates::getCountryName))
                .collect(Collectors.toList());

        model.addAttribute("rates", ratesList);

        return "ratesTable";
    }

}
