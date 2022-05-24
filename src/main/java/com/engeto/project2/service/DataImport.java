package com.engeto.project2.service;

import com.engeto.project2.entity.Rates;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class DataImport {

    private final String DATA_URL = "https://euvatrates.com/rates.json";

    @EventListener(ApplicationReadyEvent.class)
    public void download() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(DATA_URL)).build();

        String json = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        //.thenApply(App::parse)
        //.join();

        //System.out.println(json);

        try {
            parse(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parse(String jsonString) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Reader reader = new StringReader(jsonString);
        Rates all = objectMapper.readValue(reader, Rates.class);

        System.out.println(all.getRateList());

        }
}
