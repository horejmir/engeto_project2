package com.engeto.project2;

import com.engeto.project2.entity.Country;
import com.engeto.project2.service.RatesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Component
@Profile("!test")
public class CommandLineApp implements CommandLineRunner {

    @Autowired
    RatesService ratesService;

    @Autowired
    Environment environment;

    @Override
    public void run(String... args) {

        int outputSize = 3;
        String port = environment.getProperty("local.server.port");

        List<Country> allCountryList = ratesService.getAllCountry();

        List<Country> lowestCountryList = allCountryList
                .stream()
                .sorted(Comparator.comparing(Country::getStandardRate))
                .limit(outputSize)
                .collect(Collectors.toList());

        List<Country> highestCountryList = allCountryList
                .stream()
                .sorted(Comparator.comparing(Country::getStandardRate).reversed())
                .limit(outputSize)
                .collect(Collectors.toList());

        System.out.println("=============================================================================");
        System.out.println(outputSize + " COUNTRIES WITH THE HIGHEST STANDARD VAT TAX:");
        highestCountryList.forEach(System.out::println);
        System.out.println("=============================================================================");
        System.out.println(outputSize + " COUNTRIES WITH THE LOWEST STANDARD VAT TAX:");
        lowestCountryList.forEach(System.out::println);
        System.out.println("=============================================================================");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.print("SAVE OUTPUTS TO FILES? Y/N: ");
            String input = reader.readLine();

            if (input.toUpperCase().startsWith("Y")) {

                String outputFilenameHighest = "countries_highest_VAT.json";
                String outputFilenameLowest = "countries_lowest_VAT.json";
                ObjectMapper objectMapper = new ObjectMapper();

                objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValue(new File(outputFilenameHighest), highestCountryList);
                System.out.println("\t- OUTPUT FILE CRATED: '"
                        + outputFilenameHighest + "'");

                objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValue(new File(outputFilenameLowest), lowestCountryList);
                System.out.println("\t- OUTPUT FILE CRATED: '"
                        + outputFilenameLowest + "'");

            } else {
                System.out.println("\t- OUTPUT FILES NOT CREATED");
            }

            System.out.println("=============================================================================");
            System.out.println("GET COUNTRY BY SHORTCUT");
            while (true) {
                System.out.print("INSERT COUNTRY SHORTCUT (OR TYPE 'QUIT'): ");
                input = reader.readLine();

                if (input.equalsIgnoreCase("QUIT")) {
                    break;
                }
                System.out.println(ratesService.getCountryByShortcut(input.toUpperCase()).orElse(null));
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("=============================================================================");
        System.out.println("CONSOLE APP FINISHED! VISIT PROJECT WEBSITE: http://localhost:" + port + "/ OR TRY REST API: http://localhost:" + port + "/api/rates/");
    }
}
