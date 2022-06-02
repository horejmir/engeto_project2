package com.engeto.project2.dataImportSupport;

import com.engeto.project2.entity.CountryRates;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.math.NumberUtils;


import java.io.IOException;

public class CountryRatesDeserializer extends StdDeserializer<CountryRates> {

    public CountryRatesDeserializer() {
        this(null);
    }

    public CountryRatesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CountryRates deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        CountryRates countryRates = new CountryRates();

        if(node.has("iso_duplicate_of"))
            countryRates.setShortcutEU(node.get("iso_duplicate_of").asText());

        if(node.has("iso_duplicate"))
            countryRates.setShortcutISO(node.get("iso_duplicate").asText());

        countryRates.setCountryName(node.get("country").asText());
        countryRates.setStandardRate(convertToDouble(node.get("standard_rate").asText()));
        countryRates.setReducedRate(convertToDouble(node.get("reduced_rate").asText()));
        countryRates.setReducedRateAlt(convertToDouble(node.get("reduced_rate_alt").asText()));
        countryRates.setSuperReducedRate(convertToDouble(node.get("super_reduced_rate").asText()));
        countryRates.setParkingRate(convertToDouble(node.get("parking_rate").asText()));

        return countryRates;
    }

    private Double convertToDouble(String inputString) {
        if (NumberUtils.isCreatable(inputString))
            return NumberUtils.createDouble(inputString);
        else
            return null;
    }


}
