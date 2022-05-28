package com.engeto.project2.dataDownload;

import com.engeto.project2.entity.CountryRates;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class RatesDownload {

    private final String RATES_DOWNLOAD_URL = "https://euvatrates.com/rates.json";

    public List<CountryRates> get() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(RATES_DOWNLOAD_URL)).build();

        String json = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();

        ObjectMapper objectMapper = new ObjectMapper();
        Reader reader = new StringReader(json);
        Rates rates = null;

        try {
            rates = objectMapper.readValue(reader, Rates.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rates.getRatesList();
    }

}
