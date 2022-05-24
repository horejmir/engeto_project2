package com.engeto.project2.entity;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.apache.commons.lang3.math.NumberUtils;


import java.io.IOException;

public class CountryTaxEntityDeserializer extends StdDeserializer<CountryTaxEntity> {

    public CountryTaxEntityDeserializer() {
        this(null);
    }

    public CountryTaxEntityDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CountryTaxEntity deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        CountryTaxEntity countryTaxEntity = new CountryTaxEntity();

        countryTaxEntity.setCountryName(node.get("country").asText());
        countryTaxEntity.setStandardRate(convertToDouble(node.get("standard_rate").asText()));
        countryTaxEntity.setReducedRate(convertToDouble(node.get("reduced_rate").asText()));
        countryTaxEntity.setReduceRateAlt(convertToDouble(node.get("reduced_rate_alt").asText()));
        countryTaxEntity.setSuperReduceRate(convertToDouble(node.get("super_reduced_rate").asText()));
        countryTaxEntity.setParkingRate(convertToDouble(node.get("parking_rate").asText()));

        return countryTaxEntity;
    }

    private Double convertToDouble(String string) {
        if (NumberUtils.isCreatable(string))
            return NumberUtils.createDouble(string);
        else
            return null;
    }


}
