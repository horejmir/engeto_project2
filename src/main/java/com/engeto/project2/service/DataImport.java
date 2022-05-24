package com.engeto.project2.service;

import com.engeto.project2.entity.Rates;
import com.google.gson.Gson;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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

        parse(json);
    }

    private void parse(String jsonString) {

        Gson gson = new Gson();

        //String data = "{\"SE\": {\"country\": \"Sweden\", \"standard_rate\": 25.00, \"reduced_rate\": 12.00, \"reduced_rate_alt\": 6.00, \"super_reduced_rate\": false, \"parking_rate\": false}}";

        //CountryTaxEntity entity = gson.fromJson(data, CountryTaxEntity.class);

        //Type countryTaxType = new TypeToken<HashMap<String, CountryTaxEntity>>(){}.getType();

        //HashMap<String, CountryTaxEntity> map = gson.fromJson(jsonString, countryTaxType);



        //CountryTaxEntity[] entityArray = gson.fromJson(jsonString, CountryTaxEntity[].class);

        Rates all = gson.fromJson(jsonString, Rates.class);

        System.out.println(all);

        }




            /*
        String json = null;
        try {
            URL url = new URL("https://euvatrates.com/rates.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = new Scanner(inStream, "UTF-8").useDelimiter("\\Z").next();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(json);

         */
}
