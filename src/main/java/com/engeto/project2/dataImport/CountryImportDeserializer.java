package com.engeto.project2.dataImport;

import com.engeto.project2.entity.Country;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.math.NumberUtils;


import java.io.IOException;

public class CountryImportDeserializer extends StdDeserializer<Country> {

    public CountryImportDeserializer() {
        this(null);
    }

    public CountryImportDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Country deserialize(JsonParser jp, DeserializationContext context)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        Country country = new Country();

        if(node.has("iso_duplicate_of")) {
            country.setShortcutEU(node.get("iso_duplicate_of").asText());
        }

        if(node.has("iso_duplicate")) {
            country.setShortcutISO(node.get("iso_duplicate").asText());
        }


        country.setName(node.get("country").asText());
        country.setStandardRate(convertToDouble(node.get("standard_rate").asText()));
        country.setReducedRate(convertToDouble(node.get("reduced_rate").asText()));
        country.setReducedRateAlt(convertToDouble(node.get("reduced_rate_alt").asText()));
        country.setSuperReducedRate(convertToDouble(node.get("super_reduced_rate").asText()));
        country.setParkingRate(convertToDouble(node.get("parking_rate").asText()));

        return country;
    }

    private Double convertToDouble(String inputString) {
        if (NumberUtils.isCreatable(inputString)) {
            return NumberUtils.createDouble(inputString);
        } else {
            return null;
        }
    }


}
