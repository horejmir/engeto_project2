package com.engeto.project2.web;

import com.engeto.project2.entity.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;


import java.net.URI;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetByShortcut() {

        URI uri = URI.create("http://localhost:" + port + "/api/rates/");

        Country country = new Country();
        country.setShortcutEU("UA");
        country.setShortcutISO("UB");
        country.setName("Ukraine");

        restTemplate.postForEntity(uri,country, String.class);
        assertThat(restTemplate.getForObject(uri.resolve("shortcut/ua"), String.class)).contains("Ukraine");
        assertThat(restTemplate.getForObject(uri.resolve("shortcut/ub"), String.class)).contains("Ukraine");
    }

    @Test
    public void testSaveUpdateDelete() {

        URI uri = URI.create("http://localhost:" + port + "/api/rates/");

        Country countryInserted = new Country();
        countryInserted.setShortcutEU("TR");
        countryInserted.setShortcutISO("TR");
        countryInserted.setName("Turkey");

        //save
        restTemplate.postForEntity(uri,countryInserted, String.class);
        Country countryReceived1 = restTemplate.getForObject(uri.resolve("shortcut/" + countryInserted.getShortcutISO()), Country.class);
        assertThat(countryReceived1).isEqualTo(countryInserted);

        //update
        Long id = countryReceived1.getId();
        countryReceived1.setName("another country name");
        restTemplate.put(uri.resolve(id.toString()), countryReceived1);

        Country countryReceived2 = restTemplate.getForObject(uri.resolve(id.toString()), Country.class);
        assertThat(countryReceived2.getName()).isEqualTo("another country name");

        //delete
        restTemplate.delete(uri.resolve(id.toString()));
        Country countryReceived3 = restTemplate.getForObject(uri.resolve(id.toString()), Country.class);
        assertThat(countryReceived3).isEqualTo(null);
    }
}
